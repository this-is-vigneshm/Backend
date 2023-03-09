package com.staunch.tech.service;

import java.io.IOException;
import java.util.List;

import javax.mail.Multipart;

import org.springframework.web.multipart.MultipartFile;

import com.staunch.tech.dto.InventoryDto;
import com.staunch.tech.entity.Inventory;

public interface IInventoryService {
	
	Inventory createItem(InventoryDto inventoryDto , MultipartFile file) throws IOException ;
	
	Inventory getItem(int itemId);
	
	List<Inventory> getAllItems();
	
	Inventory updateItem(int itemId, InventoryDto inventoryDto);
	
	String deleteItemById(int id);

	byte[] downloadImage(int id);
}
