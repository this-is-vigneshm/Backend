package com.staunch.tech.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationsDto {
	
	@NotNull(message = "Id id empty")
	private int id;
	
	@NotBlank(message = "facCode is empty")
	private String facCode;
	
	@NotBlank(message = "Name is empty")
	private String name;
	
	private String description;
	
	@NotBlank(message = "addressLine1 id empty")
    private String addressLine1;
	@NotBlank(message = "addressLine2 id empty")
    private String addressLine2;
	
    private String addressLine3;
    @NotBlank(message = "city id empty")
    private String city;
    @NotBlank(message = "state id empty")
    private String state;
    @NotNull(message = "postalCode id empty")
    private String postalCode;
    @NotBlank(message = "country id empty")
    private String country;
    @NotNull(message = "userId id empty")
	private int userId;
}
