package com.onlyjoon.hankkioke.room.controller

import com.onlyjoon.hankkioke.common.dto.BaseResponse
import com.onlyjoon.hankkioke.room.usecase.RoomParticipateUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms")
class RoomParticipateController(
    private val roomParticipateUseCase: RoomParticipateUseCase,
) {
    /**
     * 방 참가
     */
    @PatchMapping("/{roomId}/participants/{userId}")
    fun participateRoom(
        @PathVariable("roomId") roomId: Long?,
        @PathVariable("userId") userId: Long?,
    ) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                BaseResponse(roomParticipateUseCase.participateRoom(userId, roomId))
            )
}
