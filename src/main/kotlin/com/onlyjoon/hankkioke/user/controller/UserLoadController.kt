package com.onlyjoon.hankkioke.user.controller

import com.onlyjoon.hankkioke.common.dto.BaseResponse
import com.onlyjoon.hankkioke.user.usecase.UserLoadUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserLoadController(
    private val userLoadUseCase: UserLoadUseCase,
) {
    /**
     * 사용자 정보 조회
     */
    @GetMapping("/{userId}")
    fun loadUserName(
        @PathVariable("userId") userId: Long,
    ) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                BaseResponse(userLoadUseCase.loadUserName(userId))
            )
}