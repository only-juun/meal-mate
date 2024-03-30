package com.onlyjoon.hankkioke.room.usecase

import com.onlyjoon.hankkioke.room.dto.response.RoomDetailsResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface RoomLoadUseCase {
    fun findAllRooms(pageable: Pageable): Any
    fun findRoomDetails(roomId: Long): RoomDetailsResponse
    fun getAttendeeList(roomId: Long): Any

}