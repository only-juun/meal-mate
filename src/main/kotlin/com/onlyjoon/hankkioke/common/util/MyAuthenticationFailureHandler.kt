package com.onlyjoon.hankkioke.common.util

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component

@Component
class MyAuthenticationFailureHandler : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        response?.sendRedirect("http://localhost:8080/");
    }

}