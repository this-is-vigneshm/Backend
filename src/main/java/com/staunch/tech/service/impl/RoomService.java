package com.staunch.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.FloorDto;
import com.staunch.tech.dto.RoomDto;
import com.staunch.tech.entity.Floor;
import com.staunch.tech.entity.Room;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.FloorRepository;
import com.staunch.tech.repository.RoomRepository;
import com.staunch.tech.service.IRoomService;

@Service
public class RoomService implements IRoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired 
	private FloorRepository floorRepository;
	
	@Override
	public Room createRoom(RoomDto roomDto) {
		var floor = floorRepository.findById(roomDto.getFloorId());
		return roomRepository.save(new Room(roomDto.getId(),roomDto.getName(), floor.get()));
	}

	@Override
	public Room getRoom(int roomId) {
		var roomOpt = roomRepository.findById(roomId);
		if (roomOpt.isEmpty()) {
			throw new AssetManagementException("Room Id is Invalid");
		}
		return roomOpt.get();
	}

	@Override
	public String deleteRoom(int roomId) {
		var roomOpt = roomRepository.findById(roomId);
		if (roomOpt.isEmpty()) {
			throw new AssetManagementException("Room id is Invalid");
		}
		roomRepository.deleteById(roomId);
		return "Building with id : " + roomId + " deleted successfully";
	}

	@Override
	public List<Room> getAllRoomByFloor(int floorId) {
		var floor = floorRepository.findById(floorId);
		var roomList = roomRepository.findAllByFloor(floor.get());
		if (roomList.isEmpty()) {
			throw new AssetManagementException("Room List is Empty in the given Floor Name!");
		}
		return roomList;
	}

	@Override
	public List<Room> getAllRoom() {
		var rooms = roomRepository.findAll();
		if (rooms.isEmpty()) {
			throw new AssetManagementException("Room List is Empty");
		}
		return rooms;
	}

	@Override
	public String createMultiRoom(List<RoomDto> roomDto) {
		for(var i:roomDto)
		{
			var floor = floorRepository.findById(i.getFloorId());
			roomRepository.save(new Room(i.getId(),i.getName(), floor.get()));			
		}
		return "SUCCESS";
	}
	
	@Override
    public Room updateRoom(int roomId, RoomDto roomDto) {
		try {
			if (roomId != roomDto.getId()) {
				throw new AssetManagementException("roomId in body and path are not same");
			}
			var itemOpt = floorRepository.findById(roomId);
			if (itemOpt.isEmpty()) {
				throw new AssetManagementException("roomId is Invalid");
			}
			return roomRepository.save(new Room(roomDto.getId(), roomDto.getName(), floorRepository.findById(roomDto.getFloorId()).get()));
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		
    }
}

}
