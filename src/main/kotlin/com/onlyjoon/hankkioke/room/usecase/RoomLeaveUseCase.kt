package com.onlyjoon.hankkioke.room.usecase

import org.springframework.stereotype.Service

@Service
interface RoomLeaveUseCase {
    fun leaveRoom(roomId: Long, userId: Long): Any

}
