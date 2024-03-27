package com.onlyjoon.hankkioke.user.usecase.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.onlyjoon.hankkioke.common.config.OAuthProperties
import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.common.exception.ErrorCode
import com.onlyjoon.hankkioke.common.util.JwtUtil
import com.onlyjoon.hankkioke.user.dto.response.UserResourceResponse
import com.onlyjoon.hankkioke.user.repository.UserRepository
import com.onlyjoon.hankkioke.user.usecase.UserLoginUseCase
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity
import java.util.regex.Pattern

@Service
class UserLoginService(
    private val restClient: RestClient,
    private val properties: OAuthProperties,
    private val objectMapper: ObjectMapper,
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
) : UserLoginUseCase{
    override fun socialLogin(code: String, registrationId: String): String {
        val authorization = getAuthorization(code)
        val userResource = getUserResource(authorization)
        validateEmailDomain(userResource.email)

        val user = userRepository.findByEmail(userResource.email) ?: userResource.toUser().also {
            userRepository.save(it)
        }

        return jwtUtil.generateToken(user.email)
    }

    private fun validateEmailDomain(email: String) {
        if (!(Pattern.compile("@okestro.com")).matcher(email).find()) {
            throw CustomException(ErrorCode.INVALID_EMAIL)
        }
    }

    private fun getUserResource(authorization: String): UserResourceResponse {
        val response = restClient.get()
            .uri(properties.resourceUri)
            .header(
                "Authorization" ,authorization
                )
            .retrieve()
            .toEntity<JsonNode>()

        val body = response.body ?: throw CustomException(ErrorCode.INVALID_CODE)

        return objectMapper.treeToValue(body, UserResourceResponse::class.java)

    }

    private fun getAuthorization(code: String): String {
        val response = restClient.post()
            .uri(properties.tokenUri)
            .body(
                mapOf(
                    "code" to code,
                    "client_id" to properties.clientId,
                    "client_secret" to properties.clientSecret,
                    "redirect_uri" to properties.redirectUri,
                    "grant_type" to "authorization_code"
                )
            )
            .retrieve()
            .toEntity<JsonNode>()

        val body = response.body ?: throw CustomException(ErrorCode.INVALID_CODE)
        val tokenType = body["token_type"].asText()
        val accessToken = body["access_token"].asText()

        return "$tokenType $accessToken"

    }
}