package com.staunch.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.LookUpsDto;
import com.staunch.tech.service.impl.LookUpsService;


@RestController
@RequestMapping("/look-ups")
@CrossOrigin("*")
public class LookUpsController {

	@Autowired
	private LookUpsService lookUpsService;
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> registerLookUps(@RequestBody LookUpsDto lookUpsDto)
	{
		var response = new ApiResponseDto("1200", "Success", lookUpsService.registerLookUps(lookUpsDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listLookUps()
	{
		var response = new ApiResponseDto("1200", "Success", lookUpsService.viewLookUps());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseDto> editLookUps(@RequestBody LookUpsDto lookUpsDto, @PathVariable int id)
	{
		var response = new ApiResponseDto("1200", "Success", lookUpsService.editLookUps(id, lookUpsDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseDto> deleteLookUps(@PathVariable int id)
	{
		var response = new ApiResponseDto("1200", "Success", lookUpsService.deleteLookUps(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
