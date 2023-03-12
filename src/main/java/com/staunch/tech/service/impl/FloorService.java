package com.staunch.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.FloorDto;
import com.staunch.tech.entity.Building;
import com.staunch.tech.entity.Floor;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.BuildingRepository;
import com.staunch.tech.repository.FloorRepository;
import com.staunch.tech.service.IFloorService;

@Service
public class FloorService implements IFloorService {

	@Autowired
	private FloorRepository floorRepository;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Override
	public Floor createFloor(FloorDto floorDto) {
		var building = buildingRepository.findById(floorDto.getBuildingId());
		return floorRepository.save(new Floor(floorDto.getId(),floorDto.getName(), building.get(), false));
	}

	@Override
	public String deleteFloor(int floorId) {
		var floorOpt = floorRepository.findById(floorId);
		if (floorOpt.isEmpty()) {
			throw new AssetManagementException("Floor id is Invalid");
		}
		floorRepository.deleteById(floorId);
		return "Floor with id : " + floorId + " deleted successfully";
	}
	@Override
	public List<Floor> getAllFloorByBuilding(int buildingId) {
		var building =buildingRepository.findById(buildingId);
		var floorList = floorRepository.findAllByBuilding(building.get());
		if (floorList.isEmpty()) {
			throw new AssetManagementException("Floor List is Empty in the given Location!");
		}
		return floorList;
	}

	@Override
	public List<Floor> getAllFloors() {
		var floors = floorRepository.findAll();
		if (floors.isEmpty()) {
			throw new AssetManagementException("Floor List is Empty");
		}
		return floors;
	}
	@Override
	public Floor getFloor(int floorId) {
		var floorOpt = floorRepository.findById(floorId);
		if (floorOpt.isEmpty()) {
			throw new AssetManagementException("Location Id is Invalid");
		}
		return floorOpt.get();
	}

	@Override
	public String createMultiFloor(List<FloorDto> floorDto) {
		for(var i:floorDto)
		{
			var building = buildingRepository.findById(i.getBuildingId());
			floorRepository.save(new Floor(i.getId(),i.getName(), building.get(), false));
		}
		return "SUCCESS";
	}

}
