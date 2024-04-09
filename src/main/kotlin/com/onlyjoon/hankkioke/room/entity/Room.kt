package com.onlyjoon.hankkioke.room.entity

import com.onlyjoon.hankkioke.common.entity.BaseTime
import com.onlyjoon.hankkioke.user.entity.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Room(
    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var roomId: Long? = null,

    @Column(length = 20, nullable = false)
    var title: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var description: String,

    @Column(nullable = false)
    var maxAttendees: Int,

    @Column(nullable = false)
    var mealTime: LocalDateTime,

    @Column(nullable = false)
    var restaurantName: String,

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    var category: Category,

    @Column(nullable = false)
    var restaurantLocation: String,

    @Column(nullable = false)
    var restaurantLatitude: Double,

    @Column(nullable = false)
    var restaurantLongitude: Double,

    @Column(nullable = false, columnDefinition = "TEXT")
    var locationUrl: String,

    @Column
    var deletedAt: LocalDateTime? = null,

    @Column
    var closedYn: Boolean? = null,

    @OneToMany(mappedBy = "room")
    val users: List<User> = mutableListOf(),

    ) : BaseTime()

