package com.staunch.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.entity.Reports2D;
import com.staunch.tech.entity.Reports3D;
import com.staunch.tech.service.IReports;
import com.staunch.tech.service.ITicketService;
import java.util.List;



@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportsController {
	
	@Autowired
	private IReports reportServices;
	
	@Autowired
	private ITicketService ticketServices;

	
	@GetMapping("/amount-spent")
	public ResponseEntity<ApiResponseDto> viewCalculateAmountSpent(){
		var response = new ApiResponseDto("1200", "Success", reportServices.calculateAmountSpent());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/timeline")
	public ResponseEntity<ApiResponseDto> viewTimeline(){
		var response = new ApiResponseDto("1200", "Success", reportServices.calculatWeeklyReports());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/radar")
	public ResponseEntity<ApiResponseDto> viewRadar(){
		var response = new ApiResponseDto("1200", "Success", reportServices.calculateRadarReport());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/bubble")
	public ResponseEntity<ApiResponseDto> viewBubbe(){
		var response = new ApiResponseDto("1200", "Success", reportServices.calculateBubbleReport());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/values")
	public ResponseEntity<ApiResponseDto> getvalues(@RequestParam List<String> listId){
		var response = new ApiResponseDto("1200", "Success", ticketServices.getAllTicketsById(listId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/2d/saveAll")
	public ResponseEntity<ApiResponseDto> generateReports(@RequestBody List<Reports2D> reports2d){
		var response = new ApiResponseDto("1200", "Success", reportServices.generate2D(reports2d));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/3d/saveAll")
	public ResponseEntity<ApiResponseDto> generate3DReports(@RequestBody List<Reports3D> reports3d){
		var response = new ApiResponseDto("1200", "Success", reportServices.generate3D(reports3d));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
