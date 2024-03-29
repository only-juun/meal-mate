package com.onlyjoon.hankkioke.room.mapper

import com.onlyjoon.hankkioke.common.annotation.Mapper
import com.onlyjoon.hankkioke.common.exception.CustomException
import com.onlyjoon.hankkioke.common.exception.ErrorCode
import com.onlyjoon.hankkioke.room.dto.request.RoomCreateCommand
import com.onlyjoon.hankkioke.room.dto.response.RoomCreateResponse
import com.onlyjoon.hankkioke.room.entity.Room

@Mapper
class RoomMapper {
    fun toRoom(command: RoomCreateCommand) = Room(
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

    fun toResponse(room: Room) = RoomCreateResponse(
        roomId = room.id ?: throw CustomException(ErrorCode.DATABASE_ERROR),
        title = room.title,
    )

}