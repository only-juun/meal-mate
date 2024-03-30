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
    var title: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var maxAttendees: Int,

    @Column(nullable = false)
    var mealTime: LocalDateTime,

    @Column(nullable = false, length = 60)
    var restaurantName: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var category: Category,

    @Column(nullable = false)
    var restaurantLocation: String,

    @Column(nullable = false, length = 30)
    var restaurantLatitude: Double,

    @Column(nullable = false, length = 30)
    var restaurantLongitude: Double,

    @Column(nullable = false, length = 255)
    var locationUrl: String,

    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null,

    @Column(nullable = true)
    var closedAt: LocalDateTime? = null,

    @OneToMany(mappedBy = "room")
    val users: List<User> = mutableListOf(),

) : BaseTime()