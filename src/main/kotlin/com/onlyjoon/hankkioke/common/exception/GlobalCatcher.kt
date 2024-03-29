package com.onlyjoon.hankkioke.common.exception

import com.onlyjoon.hankkioke.common.dto.BaseResponse
import com.onlyjoon.hankkioke.common.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalCatcher {

    @ExceptionHandler(CustomException::class)
    protected fun handleCustomException(customException: CustomException) : ResponseEntity<ErrorResponse> {
        val errorCode = customException.errorCode
        return ResponseEntity
            .status(errorCode.status)
            .body(
                ErrorResponse(
                message = errorCode.message)
            )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Map<String, Map<String, List<String>>>>> {
        val errors = ex.bindingResult.fieldErrors.groupBy(
            { it.field },
            { it.defaultMessage ?: "Unknown error" }
        )

        val responseBody = mapOf(
            "errors" to errors
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                BaseResponse(data = responseBody)
            )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<Map<String, Any>> {
        val responseBody = mapOf(
            "data" to mapOf(
                "error" to "잘못된 요청 형식입니다. 요청 데이터를 확인해 주세요."
            )
        )

        return ResponseEntity(responseBody, HttpStatus.BAD_REQUEST)
    }
}