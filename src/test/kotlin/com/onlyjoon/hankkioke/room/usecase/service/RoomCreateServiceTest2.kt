package com.onlyjoon.hankkioke.room.usecase.service

import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.room.dto.request.RoomCreateCommand
import com.onlyjoon.hankkioke.room.dto.response.RoomCreateResponse
import com.onlyjoon.hankkioke.room.entity.Category
import com.onlyjoon.hankkioke.room.entity.Room
import com.onlyjoon.hankkioke.room.mapper.RoomMapper
import com.onlyjoon.hankkioke.room.repository.RoomRepository
import com.onlyjoon.hankkioke.user.entity.User
import com.onlyjoon.hankkioke.user.repository.UserRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import java.time.LocalDateTime
import java.util.*

class RoomCreateServiceTest2 : BehaviorSpec({

    val roomMapper = mockk<RoomMapper>()
    val roomRepository = mockk<RoomRepository>()
    val userRepository = mockk<UserRepository>()
    val roomCreateService = RoomCreateService(roomMapper, roomRepository, userRepository)

    Given("방 생성을 위해") {

        When("방 정보가 주어졌을 때") {
            val user = User(userId = 1L, name = "John Doe", email = "john@example.com", profileImage = "image.jpg")
            val roomCreateCommand = RoomCreateCommand(
                userId = 1L,
                title = "Test Room",
                description = "This is a test room",
                mealTime = LocalDateTime.now(),
                maxAttendees = 5,
                category = Category.KOREAN,
                restaurantName = "Test Restaurant",
                restaurantLocation = "Test Location",
                restaurantLatitude = 0.0,
                restaurantLongitude = 0.0,
                locationUrl = "http://example.com"
            )
            val room = Room(
                roomId = 1L,
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
            val roomCreateResponse = RoomCreateResponse(roomId = room.roomId!!, title = room.title)

            every { userRepository.findById(1L) } returns Optional.of(user)
            every { roomMapper.roomCreateCommandToRoom(roomCreateCommand) } returns room
            every { roomMapper.roomToRoomCreateResponse(any()) } returns roomCreateResponse
            every { roomRepository.save(any()) } returns room

            val response = roomCreateService.createRoom(roomCreateCommand)

            Then("방을 저장해야 한다.") {

                verify(exactly = 1) { userRepository.findById(1L) }
                verify(exactly = 1) { roomMapper.roomCreateCommandToRoom(roomCreateCommand) }
                verify(exactly = 1) { roomRepository.save(room) }
                verify(exactly = 1) { roomMapper.roomToRoomCreateResponse(room) }
            }

            Then("사용자의 방 정보를 업데이트해야 한다.") {
                assertEquals(user.room, room)
            }

            Then("적절한 응답을 보내야 한다.") {
                assertEquals(roomCreateResponse.roomId, response.roomId)
                assertEquals(roomCreateResponse.title, response.title)
            }

        }

        When("존재하지 않는 사용자가 주어졌을 때") {
            every { userRepository.findById(any()) } returns Optional.empty()

            Then("적절한 예외 응답을 보내야 한다.") {
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
                        locationUrl = "http://example.com"
                    ))
                }
            }
        }
    }
})