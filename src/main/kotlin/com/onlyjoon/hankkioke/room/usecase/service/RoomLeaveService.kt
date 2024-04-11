package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.common.exception.ErrorCode
import com.onlyjoon.hankkioke.room.repository.RoomRepository
import com.onlyjoon.hankkioke.room.usecase.RoomLeaveUseCase
import com.onlyjoon.hankkioke.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class RoomLeaveService(
    private val userRepository: UserRepository,
    private val roomRepository: RoomRepository,
) : RoomLeaveUseCase {

    override fun leaveRoom(roomId: Long, userId: Long) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw CustomException(ErrorCode.USER_NOT_FOUND)

        val room = roomRepository.findActiveRoomById(roomId)
            ?: throw CustomException(ErrorCode.ALREADY_EMPTY_ROOM)

        val isRoomLeader = user.isRoomLeader

        user.leaveRoom()

        if (isRoomLeader) {
            user.revokeLeadership()
        }

        val remainingUsers = room.users.filter { it != user }

        if (remainingUsers.isEmpty()) {
            room.deleteRoom()
        } else if (isRoomLeader) {
            room.assignNextLeader(userId)
        }
    }
}
