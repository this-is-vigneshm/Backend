package com.staunch.tech.service;

import java.util.List;


import com.staunch.tech.dto.ResourceDto;


import com.staunch.tech.entity.Resource;


public interface IResourceService {
	Resource registerResource(ResourceDto resourceDto);

	Resource updateResource(int id, ResourceDto resourceDto);

	ResourceDto getResourceById(int id);

	List<ResourceDto> getAllResource();

	String deleteById(int id);
	
	List<Resource> getAllByworkorderCode(String workOrderCode);
	
	String registerMultiResource(List<ResourceDto> resourceDto);

}
