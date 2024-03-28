package com.onlyjoon.hankkioke.common.util

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.nio.charset.StandardCharsets

@Component
class MyAuthenticationSuccessHandler(
    private val jwtUtil: JwtUtil,
) : SimpleUrlAuthenticationSuccessHandler() {

    private val log = LoggerFactory.getLogger(MyAuthenticationSuccessHandler::class.java)

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val oAuth2User = authentication?.principal as OAuth2User
        val email = oAuth2User.getAttribute<String>("email")
        val provider = oAuth2User.getAttribute<String>("provider")
        val isExist = oAuth2User.getAttribute<Boolean>("exist") ?: false
        val role = oAuth2User.authorities.firstOrNull()?.authority
            ?: throw IllegalAccessError("Role not found")

        if (isExist) {
            val token = jwtUtil.generateToken(email!!)
            log.info("JWT Token = $token")

            val targetUrl = UriComponentsBuilder.fromUriString("http://3.39.72.204/loginSuccess")
                .queryParam("accessToken", token)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString()

            log.info("redirect 준비")
            response?.let {
                redirectStrategy.sendRedirect(request, it, targetUrl)
            }
        } else {
            val targetUrl = UriComponentsBuilder.fromUriString("http://3.39.72.204/loginSuccess")
                .queryParam("email", email)
                .queryParam("provider", provider)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString()

            response?.let {
                redirectStrategy.sendRedirect(request, it, targetUrl)
            }
        }
    }
}