package com.onlyjoon.hankkioke.user.repository

import com.onlyjoon.hankkioke.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>{
    fun findByEmail(email: String): User?
}