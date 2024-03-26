package com.onlyjoon.hankkioke.user.usecase.service

import com.onlyjoon.hankkioke.user.usecase.UserLoginUseCase
import org.springframework.stereotype.Service

@Service
class UserLoginService : UserLoginUseCase{
    override fun socialLogin(code: String) = "social login"
}