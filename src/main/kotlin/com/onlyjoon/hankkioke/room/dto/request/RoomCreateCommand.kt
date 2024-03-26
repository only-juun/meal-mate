package com.onlyjoon.hankkioke.room.dto.request

import java.time.LocalDateTime

data class RoomCreateCommand(
    val userId: Long,
    val title: String,
    val description: String,
    val restaurantName: String,
    val restaurantLocation: String,
    val locationUrl: String,
    val restaurantCategory: String,
    val restaurantLatitude: String,
    val restaurantLongitude: String,
    val mealTime: LocalDateTime,
    val maxAttendee: Int,
)