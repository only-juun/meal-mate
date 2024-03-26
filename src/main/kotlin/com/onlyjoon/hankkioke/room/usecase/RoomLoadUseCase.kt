package com.onlyjoon.hankkioke.room.usecase

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface RoomLoadUseCase {
    fun findAllRooms(pageable: Pageable?): Any
    fun findRoomDetails(roomId: Long?): Any
    fun getAttendeeList(roomId: Long): Any

}