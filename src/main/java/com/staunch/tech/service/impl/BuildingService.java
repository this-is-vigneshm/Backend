package com.staunch.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.BuildingDto;
import com.staunch.tech.entity.Building;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.BuildingRepository;
import com.staunch.tech.repository.LocationsRepository;
import com.staunch.tech.service.IBuildingService;
import com.staunch.tech.service.ILocationsService;
import com.staunch.tech.utils.ConversionUtils;

@Service
public class BuildingService implements IBuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private LocationsRepository locationRepository;
	
	@Override
	public Building createBuilding(BuildingDto buildingDto) {
		
		var location = locationRepository.findById(buildingDto.getLocationId());
		return buildingRepository.save(new Building(buildingDto.getId(),buildingDto.getName(), location.get(), false));
	}

	@Override
	public String deleteBuilding(int buildingId) {
		var buildingOpt = buildingRepository.findById(buildingId);
		if (buildingOpt.isEmpty()) {
			throw new AssetManagementException("Building id is Invalid");
		}
		buildingRepository.deleteById(buildingId);
		return "Building with id : " + buildingId + " deleted successfully";
	}

	@Override
	public List<Building> getAllBuildingByLocation(int id) {
		var loc = locationRepository.findById(id);
		var buildingList = buildingRepository.findAllByLocations(loc.get());
		if (buildingList.isEmpty()) {
			throw new AssetManagementException("Building List is Empty in the given Location!");
		}
		return buildingList;
	}

	@Override
	public List<Building> getAllBuildings() {
		var buildings = buildingRepository.findAll();
		if (buildings.isEmpty()) {
			throw new AssetManagementException("Buildings List is Empty");
		}
		return buildings;
	}

	@Override
	public Building getBuilding(int buildingId) {
		var buildingOpt = buildingRepository.findById(buildingId);
		if (buildingOpt.isEmpty()) {
			throw new AssetManagementException("Location Id is Invalid");
		}
		return buildingOpt.get();
	}

	@Override
	public String createMultiBuilding(List<BuildingDto> buildingDto) {
		for(var i:buildingDto)
		{
			var location = locationRepository.findById(i.getLocationId());
			buildingRepository.save(new Building(i.getId(),i.getName(), location.get(), false));
		}
		return "SUCCESS";
	}
	
	@Override
    public Building updateBuilding(int buildingId, BuildingDto buildingDto) {
		try {
			if (buildingId != buildingDto.getId()) {
				throw new AssetManagementException("building id in body and path are not same");
			}
			var itemOpt = buildingRepository.findById(buildingId);
			if (itemOpt.isEmpty()) {
				throw new AssetManagementException("building id is Invalid");
			}
			return buildingRepository.save(new Building(buildingDto.getId(), buildingDto.getName(), locationRepository.findById(buildingDto.getLocationId()).get(), false));
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		
    }

}
}
