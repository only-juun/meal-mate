package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.common.exception.ErrorCode
import com.onlyjoon.hankkioke.room.dto.request.RoomCreateCommand
import com.onlyjoon.hankkioke.room.dto.response.RoomCreateResponse
import com.onlyjoon.hankkioke.room.mapper.RoomMapper
import com.onlyjoon.hankkioke.room.repository.RoomRepository
import com.onlyjoon.hankkioke.room.usecase.RoomCreateUseCase
import com.onlyjoon.hankkioke.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RoomCreateService(
    private val roomMapper: RoomMapper,
    private val roomRepository: RoomRepository,
    private val userRepository: UserRepository
) : RoomCreateUseCase {

    override fun createRoom(roomCreateCommand: RoomCreateCommand): RoomCreateResponse {
        val user = userRepository.findById(roomCreateCommand.userId)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }
        val room = roomMapper.roomCreateCommandToRoom(roomCreateCommand)

        user.createRoom(room)

        return roomMapper.roomToRoomCreateResponse(roomRepository.save(room))
    }
}