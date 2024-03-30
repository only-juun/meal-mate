package com.onlyjoon.hankkioke.room.mapper

import com.onlyjoon.hankkioke.common.annotation.Mapper
import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.common.exception.ErrorCode
import com.onlyjoon.hankkioke.room.dto.request.RoomCreateCommand
import com.onlyjoon.hankkioke.room.dto.response.RoomAttendeesResponse
import com.onlyjoon.hankkioke.room.dto.response.RoomCreateResponse
import com.onlyjoon.hankkioke.room.dto.response.RoomDetailsResponse
import com.onlyjoon.hankkioke.room.entity.Room

@Mapper
class RoomMapper {
    fun roomCreateCommandToRoom(command: RoomCreateCommand) = Room(
        title = command.title,
        description = command.description,
        maxAttendees = command.maxAttendees,
        mealTime = command.mealTime,
        restaurantName = command.restaurantName,
        category = command.category,
        restaurantLocation = command.restaurantLocation,
        restaurantLatitude = command.restaurantLatitude,
        restaurantLongitude = command.restaurantLongitude,
        locationUrl = command.locationUrl,
    )

    fun roomToRoomCreateResponse(room: Room) = RoomCreateResponse(
        roomId = room.id ?: throw CustomException(ErrorCode.DATABASE_ERROR),
        title = room.title,
    )

    fun roomToRoomDetailsResponse(room: Room) = RoomDetailsResponse(
        roomId = room.id ?: throw CustomException(ErrorCode.ROOM_NOT_FOUND),
        title = room.title,
        description = room.description,
        mealTime = room.mealTime,
        maxAttendees = room.maxAttendees,
        category = room.category,
        restaurantName = room.restaurantName,
        restaurantLocation = room.restaurantLocation,
        locationUrl = room.locationUrl,
        restaurantLatitude = room.restaurantLatitude,
        restaurantLongitude = room.restaurantLongitude,
        userList = room.users.map { user ->
            RoomAttendeesResponse(
                userId = user.userId ?: throw CustomException(ErrorCode.USER_NOT_FOUND),
                name = user.name,
                email = user.email,
                image = user.image
            )
        }
    )

}