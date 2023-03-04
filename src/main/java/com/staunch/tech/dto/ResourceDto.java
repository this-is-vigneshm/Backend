package com.staunch.tech.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {


	private int resourceId;
	@NotBlank(message = "Resource Code is empty")
	private String resourceCode;
	@NotBlank(message = "resourceName is empty")
	private String resourceName;
	@NotBlank(message = "empty")
	private String resourceType;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date endDate;
	@NotNull(message = "Availability is empty")
	private String availability;
	
	private int userId;
	@NotNull(message = "WorkerOrderId is empty")
	private String workOrderCode;
	private int inventoryId;

}
