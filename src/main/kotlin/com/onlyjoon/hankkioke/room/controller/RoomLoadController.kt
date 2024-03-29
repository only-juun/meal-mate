package com.onlyjoon.hankkioke.room.controller

import com.onlyjoon.hankkioke.common.dto.BaseResponse
import com.onlyjoon.hankkioke.room.usecase.RoomLoadUseCase
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rooms")
class RoomLoadController(
    private val roomLoadUseCase: RoomLoadUseCase,
) {
    /**
     * 전체 방 목록 조회
     */
    @GetMapping("/all")
    fun findAllRoom(
        @PageableDefault(size = 10, sort = ["createAt"], direction = Sort.Direction.DESC) pageable: Pageable
    ) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                BaseResponse(roomLoadUseCase.findAllRooms(pageable))
            )

    /**
     * 방 상세 조회: 방 정보 및 방 참가자 목록
     */
    @GetMapping("/{roomId}")
    fun findRoomDetails(
        @PathVariable("roomId") roomId: Long
    ) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                BaseResponse(roomLoadUseCase.findRoomDetails(roomId))
            )

    /**
     * 방 참가자 목록 조회: 방 상세 조회와 통합 예정
     */
    @GetMapping("/{roomId}/users")
    fun getAttendeeList(@PathVariable("roomId") roomId: Long) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(
                BaseResponse(roomLoadUseCase.getAttendeeList(roomId))
            )

}
