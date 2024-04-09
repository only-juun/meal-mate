package com.onlyjoon.hankkioke.room.dto.response

data class RoomAttendeesResponse (
    val userId: Long,
    val name: String,
    val email: String,
    val profileImage: String,
)