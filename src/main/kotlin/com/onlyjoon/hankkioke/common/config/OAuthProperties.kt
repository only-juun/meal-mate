package com.onlyjoon.hankkioke.common.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("oauth2.google")
class OAuthProperties(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
    val tokenUri: String,
    val resourceUri: String,
)