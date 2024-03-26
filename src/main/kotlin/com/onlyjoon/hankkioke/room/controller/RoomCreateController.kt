package com.onlyjoon.hankkioke.room.controller

import com.onlyjoon.hankkioke.common.dto.BaseResponse
import com.onlyjoon.hankkioke.room.dto.request.RoomCreateCommand
import com.onlyjoon.hankkioke.room.usecase.RoomCreateUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms")
class RoomCreateController(
    private val roomCreateUseCase: RoomCreateUseCase,
) {
    /**
     * 방 생성
     */
    @PostMapping
    fun createRoom(
        @RequestBody roomCreateCommand: RoomCreateCommand
    ) =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                BaseResponse(roomCreateUseCase.createRoom(roomCreateCommand))
            )
}