
package com.staunch.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDto {
	private int id;
	@NotBlank(message = "Name is Empty")
	private String name;
	
	@NotBlank(message = "Code is Empty")
    private String code;
	
   
	
    @Column(name ="description")
    @NotBlank(message = "Description is Empty")
	private String description;
    
    @NotBlank(message = "Facility Code is Empty")
	private String facilityCode;
    
    private int areaId;
    
    private int roomId;
    
    private int serialNo;
    

	private String category;
	

	private String department;
	

	private String subAsset;
	

	private String system;
	

	private String supplier; 
	

	private String status;
	

	private String priority;
	

	private String make;
	

	private String model;
	

	private float price;
	
    private byte[] data;
    private String fileName;
	
	@NotNull(message = "user id is Empty!")
	private int userId;
}
