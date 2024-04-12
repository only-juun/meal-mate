package com.onlyjoon.hankkioke.room.controller

import com.onlyjoon.hankkioke.common.dto.BaseResponse
import com.onlyjoon.hankkioke.room.usecase.RoomDeleteUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms")
class RoomDeleteController(
    private val roomDeleteUseCase: RoomDeleteUseCase
) {
    /**
     * 방 삭제
     */
    @DeleteMapping("/{roomId}/users/{userId}")
    fun deleteRoom(
        @PathVariable("roomId") roomId: Long,
        @PathVariable("userId") userId: Long,
    ) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                BaseResponse(roomDeleteUseCase.deleteRoom(roomId, userId))
            )

}