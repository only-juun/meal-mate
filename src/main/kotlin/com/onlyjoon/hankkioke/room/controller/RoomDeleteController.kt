package com.onlyjoon.hankkioke.room.controller

import com.onlyjoon.hankkioke.room.usecase.RoomDeleteUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms")
class RoomDeleteController(
    private val roomDeleteUseCase: RoomDeleteUseCase
) {

    @DeleteMapping("/{roomId}/users/{userId}")
    fun deleteRoom(
        @PathVariable roomId: Long,
        @PathVariable userId: Long) {
        roomDeleteUseCase.deleteRoom(roomId, userId)
    }
}