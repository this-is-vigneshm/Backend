package com.staunch.tech.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.staunch.tech.dto.InventoryDto;
import com.staunch.tech.entity.Inventory;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.InventoryRepository;
import com.staunch.tech.service.IInventoryService;
import com.staunch.tech.utils.ConversionUtils;
import com.staunch.tech.utils.ImageUtils;
import com.staunch.tech.utils.ValidationUtils;

@Service
public class InventoryService implements IInventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private ValidationUtils validationUtils;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Inventory createItem(InventoryDto inventoryDto , MultipartFile file) throws IOException  {
		validationUtils.validate(inventoryDto);
		var userOpt = employeeRepository.findById(inventoryDto.getUserId());
		if (userOpt.isEmpty()) {
			throw new AssetManagementException("User Id is Invalid!");
		}
		var item = ConversionUtils.convertDtoToNewEntity(inventoryDto, userOpt.get().getName(),file);
		return inventoryRepository.save(item);
	}
	
	@Override
	public byte[] downloadImage(int id){
        Optional<Inventory> dbImageData = inventoryRepository.findById(id);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getData());
        return images;
    }

	@Override
	public Inventory getItem(int itemId) {
		var itemOpt = inventoryRepository.findById(itemId);
		if (itemOpt.isEmpty()) {
			throw new AssetManagementException("Item Id is Invalid");
		}
		return itemOpt.get();
	}

	@Override
	public List<Inventory> getAllItems() {
		var itemList = inventoryRepository.findAll();
		if (itemList.isEmpty()) {
			throw new AssetManagementException("Item List Empty");
		}
		return itemList;
	}

	@Override
	public Inventory updateItem(int itemId, InventoryDto inventoryDto) {
		try {
			if (itemId != inventoryDto.getId()) {
				throw new AssetManagementException("item id in body and path are not same");
			}
			validationUtils.validate(inventoryDto);
			var itemOpt = inventoryRepository.findById(inventoryDto.getId());
			if (itemOpt.isEmpty()) {
				throw new AssetManagementException("Asset id is Invalid");
			}
			var userOpt = employeeRepository.findById(inventoryDto.getUserId());
			if (userOpt.isEmpty()) {
				throw new AssetManagementException("User Id is Invalid!");
			}
			var item = ConversionUtils.convertDtoToUpdateEntity(inventoryDto,userOpt.get().getName(),itemOpt.get());
			return inventoryRepository.save(item);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}

	@Override
	public String deleteItemById(int id) {
		var assetOpt = inventoryRepository.findById(id);
		if (assetOpt.isEmpty()) {
			throw new AssetManagementException("Asset id is Invalid");
		}
		inventoryRepository.deleteById(id);
		return "Item with id : " + id + " deleted successfully";
	}

	@Override
	public String addMultiItems(List<InventoryDto> inventoryDto) {
		for(var i:inventoryDto)
		{
			var userOpt = employeeRepository.findById(i.getUserId());
			long createdTime = System.currentTimeMillis();
			inventoryRepository.save( new Inventory(i.getId(), i.getName(), i.getCode(),
					i.getDescription(), i.getQuantity(), i.getPrice(),
					i.getStatus(), "".getBytes(), "", userOpt.get().getName(), createdTime, userOpt.get().getName(), createdTime, false));
			
		}
		return "SUCCESS" ;
	}

}
