package com.onlyjoon.hankkioke.user.entity

import com.onlyjoon.hankkioke.common.entity.BaseTime
import com.onlyjoon.hankkioke.room.entity.Room
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class User(
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null,

    @Column(length = 6, nullable = false)
    var name: String,

    @Column(length = 320, nullable = false)
    var email: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var profileImage: String,

    @Column
    var deletedAt: LocalDateTime? = null,

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    var role: Role,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    var room: Room? = null,

    ) : BaseTime() {

    fun assignRoom(room: Room) {
        this.room = room
    }
}