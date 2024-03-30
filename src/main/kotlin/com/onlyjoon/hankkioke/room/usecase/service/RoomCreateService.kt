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
        // user 존재 검증 책임은 어디에서 갖는게 맞을까?
        val user = userRepository.findById(roomCreateCommand.userId)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND) }
        val room = roomMapper.roomCreateCommandToRoom(roomCreateCommand)

        user.assignRoom(room)

        // 응답 값을 변경해야 하는 경우
        // 수정해야 할 곳은 roomMapper, RoomCreateResponse 두 곳임
        // roomMapper 사용 또는 RoomCreateResponse companion object 사용
        // 둘 중 어떤 방법이 더 나을까?
        return roomMapper.roomToRoomCreateResponse(roomRepository.save(room))
    }
}