package com.onlyjoon.hankkioke.room.repository

import com.onlyjoon.hankkioke.room.entity.Room
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : JpaRepository<Room, Long> {

}