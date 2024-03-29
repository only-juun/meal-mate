package com.onlyjoon.hankkioke.room.dto.request

import com.onlyjoon.hankkioke.room.entity.Category
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime

data class RoomCreateCommand(
    @field:NotNull(message = "알 수 없는 사용자입니다.")
    val userId: Long,

    @field:Length(min = 2, max = 20, message = "제목은 최소 2자, 최대 20자까지 작성해 주세요.")
    @field:NotBlank(message = "제목은 반드시 작성해 주세요.")
    val title: String,

    @field:Length(max = 500)
    @field:NotBlank(message = "본문을 적어주세요.")
    val description: String,

    @field:NotNull(message = "식사 시간을 지정해 주세요.")
    val mealTime: LocalDateTime,

    @field:Min(value = 2, message = "인원이 너무 적어요. 최소 2명 이상 모여야해요.")
    @field:Max(value = 8, message = "인원이 너무 많아요. 최대 8명까지 모일 수 있어요.")
    val maxAttendees: Int,

    val category: Category,

    val restaurantName: String,
    val restaurantLocation: String,
    val locationUrl: String,

    val restaurantLatitude: Double,
    val restaurantLongitude: Double,
)