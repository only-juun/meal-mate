package com.onlyjoon.hankkioke.user.usecase

import org.springframework.stereotype.Service

@Service
interface UserLoadUseCase {
    fun loadUserName(userId: Long): String
}