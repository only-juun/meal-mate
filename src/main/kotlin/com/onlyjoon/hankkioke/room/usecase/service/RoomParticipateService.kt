package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.room.usecase.RoomParticipateUseCase
import org.springframework.stereotype.Service

@Service
class RoomParticipateService : RoomParticipateUseCase {
    override fun participateRoom(userId: Long?, roomId: Long?) = "participate"

}
