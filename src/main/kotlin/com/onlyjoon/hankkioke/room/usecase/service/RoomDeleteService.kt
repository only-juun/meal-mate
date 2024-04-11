package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.common.exception.ErrorCode
import com.onlyjoon.hankkioke.room.repository.RoomRepository
import com.onlyjoon.hankkioke.room.usecase.RoomDeleteUseCase
import com.onlyjoon.hankkioke.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RoomDeleteService(
    private val roomRepository: RoomRepository,
    private val userRepository: UserRepository,
) : RoomDeleteUseCase {

    override fun deleteRoom(roomId: Long, userId: Long) {
        val room = roomRepository.findActiveRoomById(roomId)
            ?: throw CustomException(ErrorCode.ALREADY_DELETED_ROOM)

        val user = userRepository.findByIdOrNull(userId)
            ?: throw CustomException(ErrorCode.USER_NOT_FOUND)

        if (!user.isRoomLeader || room.roomId != user.room?.roomId) {
            throw CustomException(ErrorCode.NO_PERMISSION_TO_DELETE)
        }

        user.revokeLeadership()

        room.users.forEach {
            it.leaveRoom()
        }

        room.deleteRoom()

    }
}