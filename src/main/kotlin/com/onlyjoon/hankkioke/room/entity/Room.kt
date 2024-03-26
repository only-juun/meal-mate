package com.onlyjoon.hankkioke.room.entity

import com.onlyjoon.hankkioke.common.entity.BaseTime
import com.onlyjoon.hankkioke.user.entity.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Room (
    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 20)
    var title: String? = null,

    @Column(nullable = false)
    var description: String? = null,

    @Column(nullable = false)
    var limitedAttendees: Int? = null,

    @Column(nullable = false)
    var mealTime: LocalDateTime? = null,

    @Column(nullable = false, length = 60)
    var restaurantName: String? = null,

    @Column(nullable = false)
    var restaurantLocation: String? = null,

    @Column(nullable = false, length = 30)
    var restaurantCategory: String? = null,

    @Column(nullable = false, length = 30)
    var restaurantLatitude: Double? = null,

    @Column(nullable = false, length = 30)
    var restaurantLongitude: Double? = null,

    @Column(nullable = false, length = 30)
    var locationUrl: String? = null,

    @Column(nullable = false, length = 30)
    var deletedAt: LocalDateTime? = null,

    @OneToMany(mappedBy = "room")
    val users: List<User> = mutableListOf(),

) : BaseTime()