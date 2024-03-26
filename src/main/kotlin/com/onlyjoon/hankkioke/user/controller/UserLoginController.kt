package com.onlyjoon.hankkioke.user.controller

import com.onlyjoon.hankkioke.common.dto.BaseResponse
import com.onlyjoon.hankkioke.user.usecase.UserLoginUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login/oauth2")
class UserLoginController(
    private val userLoginUseCase : UserLoginUseCase,
) {
    /**
     * 구글 로그인
     */
    @GetMapping("/code/{registrationId}")
    fun googleLogin(
        @RequestParam code: String,
        @PathVariable registrationId: String) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                BaseResponse(userLoginUseCase.socialLogin(code, registrationId))
            )
}