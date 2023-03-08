package com.staunch.tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.staunch.tech.entity.Floor;
import com.staunch.tech.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
 
	List<Room> findAllByFloor(Floor floor);
}
