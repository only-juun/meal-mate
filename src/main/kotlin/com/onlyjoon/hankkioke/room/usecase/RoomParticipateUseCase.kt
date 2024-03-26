package com.onlyjoon.hankkioke.room.usecase

import org.springframework.stereotype.Service

@Service
interface RoomParticipateUseCase {
    fun participateRoom(userId: Long?, roomId: Long?): Any

}
