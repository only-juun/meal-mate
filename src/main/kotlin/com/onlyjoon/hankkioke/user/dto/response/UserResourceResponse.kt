package com.onlyjoon.hankkioke.user.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.onlyjoon.hankkioke.user.entity.User

data class UserResourceResponse(
    val id: String,
    val email: String,
    val name: String,
    val picture: String,
    val locale: String,

    @JsonProperty("verified_email")
    val verifiedEmail: Boolean,

    @JsonProperty("given_name")
    val givenName: String,

    @JsonProperty("family_name")    val familyName: String

) {
    fun toUser() =  User(
        email = email,
        name = name,
        image = picture,
    )
}
