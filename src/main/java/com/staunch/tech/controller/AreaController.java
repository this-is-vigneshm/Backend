package com.staunch.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.AreaDto;
import com.staunch.tech.service.IAreaService;
import com.staunch.tech.service.impl.AreaService;

@RestController
@RequestMapping("/area")
@CrossOrigin("*")
public class AreaController {

	@Autowired
	private IAreaService areaService;
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> addArea(@RequestBody AreaDto areaDto) {
		var response = new ApiResponseDto("1200", "Success",areaService.createArea(areaDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listArea() {
		var response = new ApiResponseDto("1200", "Success", areaService.getAllArea());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@DeleteMapping("/{areaId}")
	public ResponseEntity<ApiResponseDto> deleteArea(@PathVariable("areaId") int areaId) {
		var response = new ApiResponseDto("1200", "Success", areaService.deleteArea(areaId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("floor/{floor}")
	public ResponseEntity<ApiResponseDto> listAreaByFloor(@PathVariable("floor") int floorId) {
		var response = new ApiResponseDto("1200", "Success", areaService.getAllAreaByFloor(floorId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/{areaId}")
	public ResponseEntity<ApiResponseDto> getAreaById(@PathVariable("areaId") int areaId) {
		var response = new ApiResponseDto("1200", "Success", areaService.getArea(areaId));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
}
