package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.common.exception.ErrorCode
import com.onlyjoon.hankkioke.room.dto.response.RoomDetailsResponse
import com.onlyjoon.hankkioke.room.mapper.RoomMapper
import com.onlyjoon.hankkioke.room.repository.RoomRepository
import com.onlyjoon.hankkioke.room.usecase.RoomLoadUseCase
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RoomLoadService(
    private val roomMapper: RoomMapper,
    private val roomRepository: RoomRepository,
) : RoomLoadUseCase {
    override fun findAllRooms(pageable: Pageable) = "allRooms"

    override fun findRoomDetails(roomId: Long): RoomDetailsResponse =
        roomMapper.roomToRoomDetailsResponse(
            roomRepository.findActiveRoomById(roomId)
                ?: throw CustomException(ErrorCode.ROOM_NOT_FOUND)
        )

    override fun getAttendeeList(roomId: Long) = "attendeeList"

}