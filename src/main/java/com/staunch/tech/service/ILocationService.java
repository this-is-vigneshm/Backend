package com.staunch.tech.service;

import com.staunch.tech.entity.Location;

import java.util.List;

public interface ILocationService {

    Location createFacility(Location facility);
    Location deleteFacility(long facilityId);
    Location updateFacility(long facilityId, Location facility);
    List<Location> getAllFacilities();
    String createMultiFacility(List<Location> facility);

}
