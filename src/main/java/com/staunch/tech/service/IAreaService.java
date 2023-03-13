package com.staunch.tech.service;

import java.util.List;

import com.staunch.tech.dto.AreaDto;
import com.staunch.tech.dto.RoomDto;
import com.staunch.tech.entity.Area;
import com.staunch.tech.entity.Room;


public interface IAreaService {

	Area createArea(AreaDto areaDto);
	Area getArea(int areaId);
	String deleteArea(int areaId);
	List<Area> getAllAreaByFloor(int floorId);
	List<Area> getAllArea();
	String createMultiArea(List<AreaDto> areaDto);
	Area updateArea(int areaId, AreaDto areaDto);
}
