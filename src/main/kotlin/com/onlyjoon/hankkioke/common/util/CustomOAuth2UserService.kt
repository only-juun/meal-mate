package com.onlyjoon.hankkioke.common.util

import com.onlyjoon.hankkioke.user.usecase.UserLoadUseCase
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service


@Service
class CustomOAuth2UserService(
    private val userLoadUseCase: UserLoadUseCase
): OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {

        // 기본 OAuth2UserService 객체 생성
        val oAuth2UserService = DefaultOAuth2UserService()

        // OAuth2UserService를 사용하여 OAuth2User 정보를 가져온다.
        val oAuth2User = oAuth2UserService.loadUser(userRequest)


        // 클라이언트 등록 ID(google, naver, kakao)와 사용자 이름 속성을 가져온다.
        val registrationId = userRequest.clientRegistration.registrationId
        val userNameAttributeName = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName


        // OAuth2UserService를 사용하여 가져온 OAuth2User 정보로 OAuth2Attribute 객체를 만든다.
        val oAuth2Attribute =
            OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.attributes)


        // OAuth2Attribute의 속성값들을 Map으로 반환 받는다.
        val memberAttribute: MutableMap<String, Any> = oAuth2Attribute.convertToMap()


        // 사용자 email(또는 id) 정보를 가져온다.
        val email = memberAttribute["email"].toString()

        // 이메일로 가입된 회원인지 조회한다.
        val findMember = userLoadUseCase.loadUserByEmail(email)

        if (findMember.isEmpty()) {
            // 회원이 존재하지 않을 경우, memberAttribute의 exist 값을 false로 설정
            memberAttribute["exist"] = false
            // 회원이 존재하지 않으므로 기본 권한인 ROLE_USER를 사용하여 DefaultOAuth2User 객체 생성 및 반환
            return DefaultOAuth2User(
                setOf(SimpleGrantedAuthority("ROLE_USER")), memberAttribute, "email"
            )
        }

        // 회원이 존재할경우, memberAttribute의 exist 값을 true로 넣어준다.
        memberAttribute["exist"] = true

        // 회원의 권한과, 회원속성, 속성이름을 이용해 DefaultOAuth2User 객체를 생성해 반환한다.
        return DefaultOAuth2User(
            setOf(SimpleGrantedAuthority("ROLE_USER")), memberAttribute, "email")
    }
}