package com.staunch.tech.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDto {
	
	@NotNull(message = "Building Id is Empty")
	private int id;
	
	@NotBlank(message = "Building Name is Empty")
	private String name;
	
	private int locationId;
	

}
