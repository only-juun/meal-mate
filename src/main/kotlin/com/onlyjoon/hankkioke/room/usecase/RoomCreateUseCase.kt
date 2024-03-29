package com.onlyjoon.hankkioke.room.usecase

import com.onlyjoon.hankkioke.room.dto.request.RoomCreateCommand
import com.onlyjoon.hankkioke.room.dto.response.RoomCreateResponse
import org.springframework.stereotype.Service

@Service
interface RoomCreateUseCase {
    fun createRoom(roomCreateCommand: RoomCreateCommand): RoomCreateResponse
}