package com.onlyjoon.hankkioke.room.dto.response

import com.onlyjoon.hankkioke.room.entity.Category
import java.time.LocalDateTime

data class RoomDetailsResponse(
    val roomId: Long,
    val title: String,
    val description: String,
    val mealTime: LocalDateTime,
    val maxAttendees: Int,
    val category: Category,
    val restaurantName: String,
    val restaurantLocation: String,
    val locationUrl: String,
    val restaurantLatitude: Double,
    val restaurantLongitude: Double,
    val userList: List<RoomAttendeesResponse>
)