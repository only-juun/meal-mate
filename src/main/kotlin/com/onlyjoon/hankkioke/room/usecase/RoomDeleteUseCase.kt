package com.onlyjoon.hankkioke.room.usecase

import org.springframework.stereotype.Service

@Service
interface RoomDeleteUseCase {
    fun deleteRoom(roomId: Long, userId: Long)
}