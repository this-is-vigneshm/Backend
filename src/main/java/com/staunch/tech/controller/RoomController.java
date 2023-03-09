package com.staunch.tech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.RoomDto;
import com.staunch.tech.service.impl.RoomService;

@RestController
@RequestMapping("/room")
@CrossOrigin("*")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> addRoom(@RequestBody RoomDto roomDto) {
		var response = new ApiResponseDto("1200", "Success",roomService.createRoom(roomDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listRoom() {
		var response = new ApiResponseDto("1200", "Success", roomService.getAllRoom());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@DeleteMapping("/{roomId}")
	public ResponseEntity<ApiResponseDto> deleteRoom(@PathVariable("roomId") int roomId) {
		var response = new ApiResponseDto("1200", "Success", roomService.deleteRoom(roomId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("floor/{floor}")
	public ResponseEntity<ApiResponseDto> listRoomByFloor(@PathVariable("floor") int floorId) {
		var response = new ApiResponseDto("1200", "Success", roomService.getAllRoomByFloor(floorId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/{roomId}")
	public ResponseEntity<ApiResponseDto> getItemById(@PathVariable("roomId") int roomId) {
		var response = new ApiResponseDto("1200", "Success", roomService.getRoom(roomId));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<ApiResponseDto> addMultiRoom(@RequestBody List<RoomDto> roomDto) {
		var response = new ApiResponseDto("1200", "Success",roomService.createMultiRoom(roomDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
