package com.staunch.tech.service;

import com.staunch.tech.dto.AssetByCsvDto;
import com.staunch.tech.dto.AssetDto;
import com.staunch.tech.entity.Asset;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IAssertService {
	/**
	 *
	 * @param assetDto
	 * @return
	 */
	Asset registerAsset(AssetDto assetDto, MultipartFile file) throws IOException;
	

	/**
	 *
	 * @param assetId
	 * @return
	 */
	Asset getAsset(int assetId);

	/**
	 *
	 * @return
	 */
	List<Asset> getAllAssets();

	/**
	 *
	 * @param facility
	 * @return
	 */
	List<Asset> getAllAssetsByFacility(String facility);

	/**
	 *
	 * @param assetId
	 * @param assetDto
	 * @return
	 */
	Asset updateAsset(int assetId, AssetDto assetDto, MultipartFile file) throws IOException;

	/**
	 *
	 * @param id
	 * @return
	 */
	String deleteAssetById(int id);

	/**
	 *
	 * @param file
	 * @param userId
	 * @return
	 */
	
	byte[] downloadImage(int assetId);

//	AssetByCsvDto addAssetsByCsv(MultipartFile file, int userId);
}
