package com.onlyjoon.hankkioke.user.usecase.service

import com.onlyjoon.hankkioke.user.usecase.UserLoadUseCase
import org.springframework.stereotype.Service

@Service
class UserLoadService : UserLoadUseCase {
    override fun loadUserName(userId: Long) = "load user"
}