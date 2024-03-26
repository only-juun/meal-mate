package com.onlyjoon.hankkioke.user.entity

import com.onlyjoon.hankkioke.common.entity.BaseTime
import com.onlyjoon.hankkioke.room.entity.Room
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class User(
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null,

    @Column(length = 30, nullable = false)
    var name: String,

    @Column(length = 30, nullable = false)
    var email: String,

    @Column(length = 30, nullable = false)
    var image: String,

    @Column(length = 30, nullable = false)
    var joinDate: LocalDate,

    @Column(length = 30, nullable = true)
    var deletedAt: LocalDateTime? = null,

    @JoinColumn(name = "ROOM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    var room: Room? = null

) : BaseTime()
