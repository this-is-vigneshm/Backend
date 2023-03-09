package com.staunch.tech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.AssetDto;
import com.staunch.tech.entity.Asset;
import com.staunch.tech.service.IAssertService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/assets")
@CrossOrigin("*")
public class AssetController {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private IAssertService assetService;

	@PostMapping("/save")
	public ResponseEntity<ApiResponseDto> addAsset(@RequestParam("Asset") String assetobj,
			@Param("file") MultipartFile file) throws IOException {
		var assetDto = objectMapper.readValue(assetobj, AssetDto.class);
		Asset data;
		System.out.println("File " + file);
		System.out.println("File " + file.getOriginalFilename());
//		    data = assetService.registerAsset(assetDto, file);
		data = assetService.registerAsset(assetDto, file);
		var response = new ApiResponseDto("1200", "Success", data);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}


	@GetMapping("/download/{assetId}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable int assetId ) {
		byte[] imageData = assetService.downloadImage(assetId);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);	}

	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listAssets() {
		var response = new ApiResponseDto("1200", "Success", assetService.getAllAssets());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{assetId}")
	public ResponseEntity<ApiResponseDto> updateAsset(@PathVariable("assetId") int assetId,
			@RequestParam("Asset") String assetobj,
			@Param("file") MultipartFile file) throws IOException {
		var assetDto = objectMapper.readValue(assetobj, AssetDto.class);
		Asset data;
		System.out.println("File " + file);
		System.out.println("File " + file.getOriginalFilename());
//		    data = assetService.registerAsset(assetDto, file);
		data = assetService.updateAsset(assetId,assetDto, file);
		var response = new ApiResponseDto("1200", "Success", data);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{assetId}")
	public ResponseEntity<ApiResponseDto> deleteAsset(@PathVariable("assetId") int assetId) {
		var response = new ApiResponseDto("1200", "Success", assetService.deleteAssetById(assetId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{assetId}")
	public ResponseEntity<ApiResponseDto> getAssetById(@PathVariable("assetId") int assetId) {
		var response = new ApiResponseDto("1200", "Success", assetService.getAsset(assetId));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

	@GetMapping("/facilities/{location}")
	public ResponseEntity<ApiResponseDto> listAssetsByFacility(@PathVariable("location") String location) {
		var response = new ApiResponseDto("1200", "Success", assetService.getAllAssetsByFacility(location));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//	@PostMapping("/csv/{userId}")
//	public ResponseEntity<ApiResponseDto> addAssetByCsv(@RequestParam("file") MultipartFile file,
//			@PathVariable("userId") int userId) {
//		var response = new ApiResponseDto("1200", "Success", assetService.addAssetsByCsv(file, userId));
//		return new ResponseEntity(response, HttpStatus.ACCEPTED);
//	}

}
