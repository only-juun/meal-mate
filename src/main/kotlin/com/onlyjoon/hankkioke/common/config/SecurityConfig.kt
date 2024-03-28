package com.onlyjoon.hankkioke.common.config

import com.onlyjoon.hankkioke.common.util.CustomOAuth2UserService
import com.onlyjoon.hankkioke.common.util.JwtAuthFilter
import com.onlyjoon.hankkioke.common.util.MyAuthenticationFailureHandler
import com.onlyjoon.hankkioke.common.util.MyAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val oAuth2LoginSuccessHandler: MyAuthenticationSuccessHandler,
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val jwtAuthFilter: JwtAuthFilter,
    private val oAuth2LoginFailureHandler: MyAuthenticationFailureHandler
) {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .cors{ cors ->
                cors.configurationSource { getCorsConfiguration() }
            }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { sessionManage ->
                sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { request ->
                request.requestMatchers("/**").permitAll()
                request.anyRequest().authenticated() }
            .oauth2Login { oauth2Login -> oauth2Login
                .userInfoEndpoint { userInfoEndpoint
                    -> userInfoEndpoint.userService(customOAuth2UserService) }
                .failureHandler(oAuth2LoginFailureHandler)
                .successHandler(oAuth2LoginSuccessHandler) }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    private fun getCorsConfiguration(): CorsConfiguration {
        return CorsConfiguration().apply {
            allowCredentials = true
            addAllowedOrigin("*")
            addAllowedMethod("*")
            addAllowedHeader("*")
        }
    }
}