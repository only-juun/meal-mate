package com.onlyjoon.hankkioke.room.controller

import com.onlyjoon.hankkioke.common.dto.BaseResponse
import com.onlyjoon.hankkioke.room.usecase.RoomLeaveUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms")
class RoomLeaveController(
    private val roomLeaveUseCase: RoomLeaveUseCase,
) {

    @PatchMapping("/{roomId}/participants/{userId}")
    fun removeUserFromRoom(
        @PathVariable roomId: Long,
        @PathVariable userId: Long
    ) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                BaseResponse(roomLeaveUseCase.leaveRoom(roomId, userId))
            )

}