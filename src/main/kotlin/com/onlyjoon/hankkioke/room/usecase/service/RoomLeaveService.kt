package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.room.usecase.RoomLeaveUseCase
import org.springframework.stereotype.Service

@Service
class RoomLeaveService : RoomLeaveUseCase {
    override fun leaveRoom(roomId: Long, userId: Long) = "leave"
}
