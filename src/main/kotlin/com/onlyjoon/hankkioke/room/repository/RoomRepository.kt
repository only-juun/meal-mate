package com.onlyjoon.hankkioke.room.repository

import com.onlyjoon.hankkioke.room.entity.Room
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : JpaRepository<Room, Long> {

    @Query( "SELECT r " +
            "FROM Room r " +
            "WHERE 1 = 1 " +
            "AND r.roomId = :roomId " +
            "AND r.deletedAt IS NULL ")
    fun findActiveRoomById(roomId: Long): Room?

}