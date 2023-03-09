package com.staunch.tech.controller;

import java.io.IOException;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.InventoryDto;
import com.staunch.tech.entity.Inventory;
import com.staunch.tech.service.impl.InventoryService;

@RestController
@RequestMapping("/inventory")
@CrossOrigin("*")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostMapping("/save")
    public ResponseEntity<ApiResponseDto> addItems( 
        @RequestParam("inventory") String inventoryObj,
        @Param("file") MultipartFile file) throws IOException {
    var inventoryDto = objectMapper.readValue(inventoryObj, InventoryDto.class);
    Inventory data;
    System.out.println("File "+ file);
    System.out.println("File "+  file.getOriginalFilename());
    data = inventoryService.createItem(inventoryDto, file);
    var response = new ApiResponseDto("1200", "Success", data);
    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listItem() {
		var response = new ApiResponseDto("1200", "Success", inventoryService.getAllItems());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@PutMapping("/{itemId}")
	public ResponseEntity<ApiResponseDto> updateItem(@PathVariable("itemId") int id,
			@RequestBody InventoryDto inventoryDto) {
		var response = new ApiResponseDto("1200", "Success", inventoryService.updateItem(id, inventoryDto));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<ApiResponseDto> deleteItem(@PathVariable("itemId") int itemId) {
		var response = new ApiResponseDto("1200", "Success", inventoryService.deleteItemById(itemId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<ApiResponseDto> getItemById(@PathVariable("itemId") int itemId) {
		var response = new ApiResponseDto("1200", "Success", inventoryService.getItem(itemId));
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

}
