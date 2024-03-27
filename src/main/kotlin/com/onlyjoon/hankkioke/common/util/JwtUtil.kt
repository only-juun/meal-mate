package com.onlyjoon.hankkioke.common.util

import com.onlyjoon.hankkioke.common.annotation.Util
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*

@Util
class JwtUtil {

    private val secretKey = Base64.getEncoder()
        .encodeToString("930x2i9nb09684s5nm9iq246mo670o2".toByteArray())

    fun generateToken(loginId: String) : String {
        val tokenPeriod = 1000L * 60L * 60L * 24L

        val claims = Jwts.claims().setSubject(loginId)
        claims["role"] = "USER"

        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenPeriod))
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .compact()
    }

    fun verifyToken(token: String) =
        // exception filter를 빼고 전역 처리를 할 방법을 찾아보기
        // db에 조회하지 않고, 인증이 한번 되었다면 캐싱 처리 해보는 건 어떤지
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
                .build()
                .parseClaimsJws(token)
                .body
                .expiration.after(Date())
        } catch (e: Exception) {
            false
        }

    fun extractEmail(token: String): String =
        Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .build()
            .parseClaimsJws(token)
            .body.subject

    fun extractRole(token: String) =
        Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .build()
            .parseClaimsJws(token)
            .body["role"]
}