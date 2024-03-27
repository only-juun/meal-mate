package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.room.usecase.RoomLoadUseCase
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RoomLoadService : RoomLoadUseCase {
    override fun findAllRooms(pageable: Pageable) = "allRooms"
    override fun findRoomDetails(roomId: Long) = "roomDetails"
    override fun getAttendeeList(roomId: Long) = "attendeeList"

}