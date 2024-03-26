package com.onlyjoon.hankkioke.common.exception

import com.onlyjoon.hankkioke.common.exception.ErrorCode

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()