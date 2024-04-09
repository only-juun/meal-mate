package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.room.usecase.RoomLeaveUseCase
import org.springframework.stereotype.Service

@Service
class RoomLeaveService : RoomLeaveUseCase {
    override fun leaveRoom(roomId: Long, userId: Long): String {
        TODO("유저 정보에서 방 정보 제거")

        TODO("새로운 방장 선임 - 입장순")

        return "leave"
    }
}
