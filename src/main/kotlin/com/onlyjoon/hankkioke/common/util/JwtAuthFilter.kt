package com.onlyjoon.hankkioke.common.util

import com.onlyjoon.hankkioke.user.repository.UserRepository
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtUtil: JwtUtil,
    private val memberRepository: UserRepository
) : OncePerRequestFilter() {

    private val logger = LoggerFactory.getLogger(JwtAuthFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val atc = request.getHeader("Authorization")

        if (!StringUtils.hasText(atc)) {
            filterChain.doFilter(request, response)
            return
        }

        if (!jwtUtil.verifyToken(atc)) {
            throw JwtException("Access Token expired!")
        }

        jwtUtil.verifyToken(atc).let {
            val findMember = memberRepository.findByEmail(jwtUtil.extractEmail(atc)) ?: throw IllegalStateException("User not found!")

            val userDto = SecurityUserDto(
                memberNo = findMember.userId!!,
                email = findMember.email,
                nickname = findMember.name
            )

            val auth = getAuthentication(userDto)
            SecurityContextHolder.getContext().authentication = auth
        }

        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(member: SecurityUserDto) =
        UsernamePasswordAuthenticationToken(
            member, null, listOf(SimpleGrantedAuthority("USER"))
        )

}