package com.zoosickcompany.userservice.common.exception

import com.onlyjoon.hankkioke.common.exception.ErrorCode

class JwtException(
    val errorCode: ErrorCode
) : RuntimeException()