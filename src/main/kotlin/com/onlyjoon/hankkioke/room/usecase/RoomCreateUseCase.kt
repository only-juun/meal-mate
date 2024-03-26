package com.onlyjoon.hankkioke.room.usecase

import com.onlyjoon.hankkioke.room.dto.request.RoomCreateCommand
import org.springframework.stereotype.Service

@Service
interface RoomCreateUseCase {
    fun createRoom(roomCreateCommand: RoomCreateCommand): String
}