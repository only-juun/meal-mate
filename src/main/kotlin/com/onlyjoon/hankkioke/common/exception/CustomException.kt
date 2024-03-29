package com.onlyjoon.hankkioke.common.exception

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()