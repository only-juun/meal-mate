//package com.onlyjoon.hankkioke.common.util
//
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.security.Keys
//import jakarta.annotation.PostConstruct
//import org.springframework.stereotype.Service
//import java.util.*
//
//@Service
//class JwtUtil2(
//    private val jwtProperties: JwtProperties,
//    private val tokenService: RefreshTokenService
//) {
//    private lateinit var secretKey: String
//
//    @PostConstruct
//    private fun init() {
//        secretKey = Base64.getEncoder().encodeToString(jwtProperties.secret.toByteArray())
//    }
//
//    fun generateToken(email: String, role: String): Set<String> {
//        val refreshToken = generateRefreshToken(email, role)
//        val accessToken = generateAccessToken(email, role)
//
//        tokenService.saveTokenInfo(email, refreshToken, accessToken)
//        return setOf(accessToken, refreshToken)
//    }
//
//    private fun generateRefreshToken(email: String, role: String): String {
//        val refreshPeriod = 1000L * 60 * 60 * 24 * 14 // 2 weeks
//        val claims = Jwts.claims().setSubject(email).apply {
//            put("role", role)
//        }
//        val now = Date()
//
//        return Jwts.builder()
//            .setClaims(claims)
//            .setIssuedAt(now)
//            .setExpiration(Date(now.time + refreshPeriod))
//            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))
//            .compact()
//    }
//
//    private fun generateAccessToken(email: String, role: String): String {
//        val tokenPeriod = 1000L * 60 * 30 // 30 minutes
//        val claims = Jwts.claims().setSubject(email).apply {
//            put("role", role)
//        }
//        val now = Date()
//
//        return Jwts.builder()
//            .setClaims(claims)
//            .setIssuedAt(now)
//            .setExpiration(Date(now.time + tokenPeriod))
//            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))
//            .compact()
//    }
//
//    fun verifyToken(token: String): Boolean {
//        return try {
//            val claims = Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
//                .build()
//                .parseClaimsJws(token)
//            claims.body.expiration.after(Date())
//        } catch (e: Exception) {
//            false
//        }
//    }
//
//    fun getUid(token: String): String =
//        Jwts.parserBuilder()
//            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
//            .build()
//            .parseClaimsJws(token)
//            .body.subject
//
//    fun getRole(token: String): String =
//        Jwts.parserBuilder()
//            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
//            .build()
//            .parseClaimsJws(token)
//            .body["role", String::class.java]
//}