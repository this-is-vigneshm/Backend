package com.staunch.tech.service;

import java.util.List;

import com.staunch.tech.dto.LocationsDto;
import com.staunch.tech.entity.Location;
import com.staunch.tech.entity.Locations;

public interface ILocationsService {

	Locations createLocation(LocationsDto locationsDto);
	
	List<Locations>  getLocation(int locationId);
	
	List<Locations> getAllLocations();
	
	Locations UpdateLocations(int locationId, LocationsDto locationsDto);
	
	List<Locations> findByCode(String facCode);
	
	String deleteLocationById(int locationId);
	
	String createMultiLocation(List<LocationsDto> locationDto);
}
