package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.room.dto.request.RoomCreateCommand
import com.onlyjoon.hankkioke.room.usecase.RoomCreateUseCase
import org.springframework.stereotype.Service

@Service
class RoomCreateService : RoomCreateUseCase {
    override fun createRoom(roomCreateCommand: RoomCreateCommand) = "created"
}