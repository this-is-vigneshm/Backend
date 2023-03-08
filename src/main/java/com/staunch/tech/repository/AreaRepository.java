package com.staunch.tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staunch.tech.entity.Area;
import com.staunch.tech.entity.Floor;

public interface AreaRepository extends JpaRepository<Area, Integer> {
	
	List<Area> findAllByFloor(Floor floor);

}
