package com.onlyjoon.hankkioke.user.usecase

import org.springframework.stereotype.Service

@Service
interface UserLoginUseCase {
    fun socialLogin(code: String, registrationId: String): String
}