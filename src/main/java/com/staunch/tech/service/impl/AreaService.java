package com.staunch.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.staunch.tech.dto.AreaDto;
import com.staunch.tech.dto.RoomDto;
import com.staunch.tech.entity.Area;
import com.staunch.tech.entity.Room;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.AreaRepository;
import com.staunch.tech.repository.FloorRepository;
import com.staunch.tech.service.IAreaService;

@Service
public class AreaService implements IAreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired 
	private FloorRepository floorRepository;
	
	@Override
	public Area createArea(AreaDto areaDto) {
		var floor = floorRepository.findById(areaDto.getFloorId());
		return areaRepository.save(new Area(areaDto.getId(),areaDto.getName(), floor.get()));
	}

	@Override
	public Area getArea(int areaId) {
		var areaOpt = areaRepository.findById(areaId);
		if (areaOpt.isEmpty()) {
			throw new AssetManagementException("Area Id is Invalid");
		}
		return areaOpt.get();
	}

	 @Override
	public String deleteArea(int areaId) {
		var roomOpt = areaRepository.findById(areaId);
		if (roomOpt.isEmpty()) {
			throw new AssetManagementException("Area id is Invalid");
		}
		areaRepository.deleteById(areaId);
		return "Area with id : " + areaId + " deleted successfully";
	}

	@Override
	public List<Area> getAllAreaByFloor(int floorId) {
		var floor = floorRepository.findById(floorId);
		var areaList = areaRepository.findAllByFloor(floor.get());
		if (areaList.isEmpty()) {
			throw new AssetManagementException("Area List is Empty in the given Location!");
		}
		return areaList;
	}

	@Override
	public List<Area> getAllArea() {
		var area = areaRepository.findAll();
		if (area.isEmpty()) {
			throw new AssetManagementException("Area List is Empty");
		}
		return area;
	}

	@Override
	public String createMultiArea(List<AreaDto> areaDto) {
		for(var i:areaDto)
		{
			var floor = floorRepository.findById(i.getFloorId());
			areaRepository.save(new Area(i.getId(),i.getName(), floor.get()));
		}
		return "SUCCESS";
	}
	
	@Override
    public Area updateArea(int areaId, AreaDto areaDto) {
		try {
			if (areaId != areaDto.getId()) {
				throw new AssetManagementException("areaId in body and path are not same");
			}
			var itemOpt = floorRepository.findById(areaId);
			if (itemOpt.isEmpty()) {
				throw new AssetManagementException("areaId is Invalid");
			}
			return areaRepository.save(new Area(areaDto.getId(), areaDto.getName(), floorRepository.findById(areaDto.getFloorId()).get()));
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		
    }
}

}
