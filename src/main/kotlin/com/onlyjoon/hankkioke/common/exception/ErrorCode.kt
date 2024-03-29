package com.onlyjoon.hankkioke.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class ErrorCode(
    val status: HttpStatus,
    val message: String,
) {

    USER_NOT_FOUND(BAD_REQUEST, "해당하는 회원이 없습니다. 확인 후 다시 시도해주시기 바랍니다."),
    PASSWORD_INCORRECT(BAD_REQUEST, "비밀번호를 잘못 입력했습니다. 확인 후 다시 시도해주시기 바랍니다."),
    INVALID_TOKEN(BAD_REQUEST, "유효하지 않은 토큰입니다. 확인 후 다시 시도해주시기 바랍니다."),
    TERMS_NOT_AGREED(BAD_REQUEST, "필수 약관에 동의하지 않았습니다. 약관에 동의 후 다시 시도해주시기 바랍니다."),
    ALREADY_EXIST_EMAIL(BAD_REQUEST, "이미 존재하는 이메일입니다. 이메일 확인 후 다시 시도해주시기 바랍니다."),
    INVALID_CODE(BAD_REQUEST, "사용할 수 없는 인가 코드입니다."),
    INVALID_EMAIL(FORBIDDEN, "해당 이메일은 사용하실 수 없습니다."),
    DATABASE_ERROR(INTERNAL_SERVER_ERROR, "데이터베이스에 접근할 수 없습니다. 확인 후 다시 시도해주시기 바랍니다.")
}