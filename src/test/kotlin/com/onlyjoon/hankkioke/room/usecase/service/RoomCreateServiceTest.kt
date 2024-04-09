package com.onlyjoon.hankkioke.room.usecase.service

import com.appmattus.kotlinfixture.kotlinFixture
import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.room.dto.request.RoomCreateCommand
import com.onlyjoon.hankkioke.room.dto.response.RoomCreateResponse
import com.onlyjoon.hankkioke.room.entity.Category
import com.onlyjoon.hankkioke.room.entity.Room
import com.onlyjoon.hankkioke.room.mapper.RoomMapper
import com.onlyjoon.hankkioke.room.repository.RoomRepository
import com.onlyjoon.hankkioke.user.entity.User
import com.onlyjoon.hankkioke.user.repository.UserRepository
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import java.time.LocalDateTime
import java.util.*

class RoomCreateServiceTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val fixture = kotlinFixture()
    val roomMapper = mockk<RoomMapper>()
    val roomRepository = mockk<RoomRepository>()
    val userRepository = mockk<UserRepository>()
    val roomCreateService = RoomCreateService(roomMapper, roomRepository, userRepository)

    given("방 생성을 위해") {

        `when`("방 정보가 주어졌을 때") {
            val userId = fixture<Long>()
            val user = fixture<User> {
                property(User::userId) { userId }
            }
            val roomCreateCommand = fixture<RoomCreateCommand> {
                property(RoomCreateCommand::userId) { userId }
                property(RoomCreateCommand::mealTime) { LocalDateTime.now() }
                property(RoomCreateCommand::category) { Category.KOREAN }
            }

            val room = Room(
                roomId = fixture<Long>(),
                title = roomCreateCommand.title,
                description = roomCreateCommand.description,
                maxAttendees = roomCreateCommand.maxAttendees,
                mealTime = roomCreateCommand.mealTime,
                restaurantName = roomCreateCommand.restaurantName,
                category = roomCreateCommand.category,
                restaurantLocation = roomCreateCommand.restaurantLocation,
                restaurantLatitude = roomCreateCommand.restaurantLatitude,
                restaurantLongitude = roomCreateCommand.restaurantLongitude,
                locationUrl = roomCreateCommand.locationUrl
            )

            val expectedResponse = RoomCreateResponse(roomId = room.roomId!!, title = room.title)

            every { userRepository.findById(userId) } returns Optional.of(user)
            every { roomMapper.roomCreateCommandToRoom(roomCreateCommand) } returns room
            every { roomMapper.roomToRoomCreateResponse(any()) } returns expectedResponse
            every { roomRepository.save(any()) } returns room

            val response = roomCreateService.createRoom(roomCreateCommand)

            then("방을 저장해야 한다.") {

                verify(exactly = 1) { userRepository.findById(userId) }
                verify(exactly = 1) { roomMapper.roomCreateCommandToRoom(roomCreateCommand) }
                verify(exactly = 1) { roomRepository.save(room) }
                verify(exactly = 1) { roomMapper.roomToRoomCreateResponse(room) }

            }

            then("사용자의 방 정보를 업데이트해야 한다.") {
                assertEquals(user.room, room)
            }

            then("적절한 응답을 보내야 한다.") {
                assertEquals(expectedResponse.roomId, response.roomId)
                assertEquals(expectedResponse.title, response.title)
            }
        }

        `when`("the user is not found") {
            every { userRepository.findById(any()) } returns Optional.empty()

            then("it should throw CustomException") {
                assertThrows(CustomException::class.java) {
                    roomCreateService.createRoom(RoomCreateCommand(
                        userId = 999L, // Assuming this ID does not exist
                        title = "Test Room",
                        description = "This is a test description",
                        mealTime = LocalDateTime.now(),
                        maxAttendees = 3,
                        category = Category.KOREAN,
                        restaurantName = "Nonexistent Restaurant",
                        restaurantLocation = "Nonexistent Location",
                        restaurantLatitude = 0.0,
                        restaurantLongitude = 0.0,
                        locationUrl = "https://example.com"
                    ))
                }
            }
        }
    }
})
