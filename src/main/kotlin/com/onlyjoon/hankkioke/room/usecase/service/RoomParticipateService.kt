package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.common.exception.ErrorCode
import com.onlyjoon.hankkioke.room.repository.RoomRepository
import com.onlyjoon.hankkioke.room.usecase.RoomParticipateUseCase
import com.onlyjoon.hankkioke.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class RoomParticipateService(
    private val userRepository: UserRepository,
    private val roomRepository: RoomRepository
) : RoomParticipateUseCase {

    override fun participateRoom(userId: Long, roomId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }
        val room = roomRepository.findActiveRoomById(roomId) ?: throw CustomException(ErrorCode.ROOM_NOT_FOUND)

        user.joinRoom(room)
    }

}
