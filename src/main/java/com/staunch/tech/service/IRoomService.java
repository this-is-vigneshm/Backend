package com.staunch.tech.service;

import java.util.List;

import com.staunch.tech.dto.RoomDto;
import com.staunch.tech.entity.Room;

public interface IRoomService {

	Room createRoom(RoomDto roomDto);
	Room getRoom(int roomId);
	String deleteRoom(int roomId);
	List<Room> getAllRoomByFloor(int floorId);
	List<Room> getAllRoom();
	}
