package com.onlyjoon.hankkioke.common.util

data class OAuth2Attribute(
    val attributes: Map<*, *>,
    val attributeKey: String,
    val email: String,
    val name: String,
    val picture: String,
    val provider: String
) {
    companion object {
        fun of(provider: String, attributeKey: String, attributes: Map<String, Any>) = when (provider) {
            "google" -> ofGoogle(provider, attributeKey, attributes)
            "kakao" -> ofKakao(provider, "email", attributes)
            "naver" -> ofNaver(provider, "id", attributes)
            else -> throw RuntimeException("Unsupported provider: $provider")
        }

        private fun ofGoogle(provider: String, attributeKey: String, attributes: Map<String, Any>) =
            OAuth2Attribute(
                email = attributes["email"] as String,
                provider = provider,
                attributes = attributes,
                attributeKey = attributeKey,
                name = attributes["name"] as String, // 가정
                picture = attributes["picture"] as String // 가정
            )

        private fun ofKakao(provider: String, attributeKey: String, attributes: Map<String, Any>): OAuth2Attribute {
            val kakaoAccount = attributes["kakao_account"] as Map<*, *>
            val kakaoProfile = kakaoAccount["profile"] as Map<*, *>

            return OAuth2Attribute(
                email = kakaoAccount["email"] as String,
                provider = provider,
                attributes = kakaoAccount,
                attributeKey = attributeKey,
                name = kakaoProfile["name"] as String, // 가정
                picture = kakaoProfile["picture"] as String // 가정
            )
        }

        private fun ofNaver(provider: String, attributeKey: String, attributes: Map<String, Any>): OAuth2Attribute {
            val response = attributes["response"] as Map<*, *>

            return OAuth2Attribute(
                email = response["email"] as String,
                provider = provider,
                attributes = response,
                attributeKey = attributeKey,
                name = response["name"] as String, // 가정
                picture = response["picture"] as String // 가정
            )
        }
    }

    fun convertToMap(): MutableMap<String, Any> = mutableMapOf(
        "id" to attributeKey,
        "key" to attributeKey,
        "email" to email,
        "provider" to provider
    )
}