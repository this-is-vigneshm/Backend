package com.staunch.tech.utils;

import com.staunch.tech.dto.*;
import com.staunch.tech.entity.Area;
import com.staunch.tech.entity.Asset;
import com.staunch.tech.entity.Employee;
import com.staunch.tech.entity.Inventory;
import com.staunch.tech.entity.KnowledgeRepo;
import com.staunch.tech.entity.Location;
import com.staunch.tech.entity.Locations;
import com.staunch.tech.entity.LookUps;
import com.staunch.tech.entity.Resource;
import com.staunch.tech.entity.Room;
import com.staunch.tech.entity.Ticket;
import com.staunch.tech.entity.WorkOrder;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class ConversionUtils {

	public static Asset convertDtoToNewEntity(AssetDto assetDto, Location location, String name, MultipartFile file)
			throws IOException {
		long createdTime = System.currentTimeMillis();
		byte[] data = ImageUtils.compressImage(file.getBytes());
		String fileName = file.getOriginalFilename();
		return new Asset(assetDto.getId(), assetDto.getName(), assetDto.getCode(),
				assetDto.getDescription(), location, assetDto.getAreaId(), assetDto.getRoomId(),  assetDto.getSerialNo(),assetDto.getCategory(),
				assetDto.getDepartment(), assetDto.getSubAsset(), assetDto.getSystem(), assetDto.getSupplier(),
				assetDto.getStatus(), assetDto.getPriority(), assetDto.getMake(), assetDto.getModel(),
				assetDto.getPrice(), data, fileName, name, createdTime, name, createdTime, false);
	}

	public static Asset convertDtoToUpdateEntity(AssetDto assetDto, Location location, String updatedBy,
			Asset existingAsset, MultipartFile file) throws IOException {
		long updatedTime = System.currentTimeMillis();
		long createdTime = existingAsset.getCreatedTime();
		String createdBy = existingAsset.getCreatedBy();
		var data = ImageUtils.compressImage(file.getBytes());
		var name = file.getOriginalFilename();
		return new Asset(assetDto.getId(), assetDto.getName(), assetDto.getCode(),
				assetDto.getDescription(), location, assetDto.getAreaId(), assetDto.getRoomId(), assetDto.getSerialNo(), assetDto.getCategory(),
				assetDto.getDepartment(), assetDto.getSubAsset(), assetDto.getSystem(), assetDto.getSupplier(),
				assetDto.getStatus(), assetDto.getPriority(), assetDto.getMake(), assetDto.getModel(),
				assetDto.getPrice(), data, name, createdBy, createdTime, updatedBy, updatedTime, false);
	}
	public static Employee convertDtoToNewEntity(EmployeeDto employeeDto, String createdBy) {
		long createdTime = System.currentTimeMillis();
		return new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getEmail(),
				employeeDto.getUsername(), employeeDto.getPhoneNumber(), employeeDto.getDepartment(),
				employeeDto.getDesignation(), employeeDto.getRoles(), employeeDto.getAddress(),
				employeeDto.getLocation(), employeeDto.getUsertype(), employeeDto.getResourceplanner(),
				employeeDto.getStatus(), employeeDto.getPassword(), createdBy, createdTime, createdBy, createdTime,
				false);
	}

	/**
	 *
	 * @param employeeDto
	 * @param updatedBy
	 * @param employee
	 * @return
	 */
	public static Employee convertDtoToUpdateEntity(EmployeeUpdateReqDto employeeDto, String updatedBy,
			Employee employee) {
		long updatedTime = System.currentTimeMillis();
		long createdTime = employee.getCreatedTime();
		String createdBy = employee.getCreatedBy();
		/*
		 * String Name = employeeDto.getName() == null ? employee.getName() :
		 * employeeDto.getName(); String Email = employeeDto.getEmail() == null ?
		 * employee.getEmail() : employeeDto.getEmail(); long phoneNumber =
		 * employeeDto.getPhoneNumber()!= null ? employee.getPhoneNumber() :
		 * employeeDto.getPhoneNumber(); String department = employeeDto.getDepartment()
		 * == null ? employee.getDepartment() : employeeDto.getDepartment(); String
		 * address = employeeDto.getAddress() == null ? employee.getAddress() :
		 * employeeDto.getAddress(); String location = employeeDto.getLocation() == null
		 * ? employee.getLocation() : employeeDto.getLocation(); String workertype =
		 * employeeDto.getUsertype() == null ? employee.getUsertype() :
		 * employeeDto.getUsertype();
		 */

		return new Employee(employee.getId(), employee.getName(), employee.getEmail(), employee.getUsername(),
				employee.getPhoneNumber(), employee.getDepartment(), employee.getDesignation(), employee.getRoles(),
				employee.getAddress(), employee.getLocation(), employee.getUsertype(), employee.getResourceplanner(),
				employee.getStatus(), employee.getPassword(), createdBy, createdTime, updatedBy, updatedTime, false);
	}

	public static Ticket convertDtoToNewEntity(TicketDto ticketDto, Employee employee, String createdBy) {
		long createdTime = System.currentTimeMillis();
		return new Ticket(UUID.randomUUID().toString(), ticketDto.getTitle(), ticketDto.getDescription(),
				ticketDto.getCategory(), "Open", employee, ticketDto.getIssueType(), ticketDto.getAssetId(),
				ticketDto.getWorkOrderId(), "".getBytes(), "", createdBy, createdTime,
				createdBy, createdTime, 0, 0, ticketDto.getExpectedCompletionTime());
	}

	public static Ticket convertDtoToNewEntity1(TicketDto ticketDto, Employee employee, String createdBy,byte[] data ,String name) throws IOException {
		long createdTime = System.currentTimeMillis();
		return new Ticket(UUID.randomUUID().toString(), ticketDto.getTitle(), ticketDto.getDescription(),
				ticketDto.getCategory(), "Open", employee, ticketDto.getIssueType(), ticketDto.getAssetId(),
				ticketDto.getWorkOrderId(), data, name, createdBy, createdTime, createdBy, createdTime, 0, 0,
				ticketDto.getExpectedCompletionTime());
	}

	public static Ticket convertDtoToUpdateEntity(TicketDto ticketDto, Employee employee, String updatedBy) {
		long updatedTime = System.currentTimeMillis();
		long createdTime = employee.getCreatedTime();
		String createdBy = employee.getCreatedBy();
		return new Ticket(ticketDto.getUuid(), ticketDto.getTitle(), ticketDto.getDescription(),
				ticketDto.getCategory(), ticketDto.getStatus(), employee, ticketDto.getIssueType(),
				ticketDto.getAssetId(), ticketDto.getWorkOrderId(), ticketDto.getData(), ticketDto.getFileName(),
				createdBy, createdTime, updatedBy, updatedTime, 0, 0, ticketDto.getExpectedCompletionTime());
	}

	public static TicketRespDto convertEntityToRespDto(Ticket ticket) {
		return new TicketRespDto(ticket.getUuid(), ticket.getTitle(), ticket.getDescription(), ticket.getCategory(),
				ticket.getStatus(), ticket.getEmployeeId().getId(), ticket.getEmployeeId().getName(),
				ticket.getEmployeeId().getEmail(), ticket.getEmployeeId().getDepartment(), ticket.getIssueType(),
				ticket.getAssetId(), ticket.getWorkOrderId(), ticket.getCreatedBy(),
				ticket.getCreatedTime(), ticket.getUpdatedBy(), ticket.getUpdatedTime(), ticket.getTimeTaken(), false,
				ticket.getExpectedCompletionTime());
	}

	public static EmployeeRespDto convertEntityToDto(Employee employee) {
		return new EmployeeRespDto(employee.getId(), employee.getName(), employee.getEmail(), employee.getUsername(),
				employee.getPhoneNumber(), employee.getDepartment(), employee.getDesignation(), employee.getRoles(),
				employee.getAddress(), employee.getLocation(), employee.getUsertype(), employee.getResourceplanner(),
				employee.getStatus(), false, employee.getPassword());
	}

	public static KRResponseDto convertFilenameToDto(KnowledgeRepoDto dto, String filename, int id, long time) {

		return new KRResponseDto(dto.getId(), filename, dto.getAsset_name(), id, time);
	}

	public static KRResponseDto convertKnoledgeRepoToDto(KnowledgeRepo repo) {
		return new KRResponseDto(repo.getId(), repo.getName(), repo.getAsset_name(), repo.getId(),
				repo.getUploaded_time());
	}

	public static Resource convertDtoToNewEntity(ResourceDto resourceDto, Integer userId) {
		return new Resource(resourceDto.getResourceId(), resourceDto.getResourceCode(), resourceDto.getResourceName(),
				resourceDto.getResourceType(), resourceDto.getStartDate(), resourceDto.getEndDate(),
				resourceDto.getAvailability(), userId, resourceDto.getWorkOrderCode(), resourceDto.getInventoryId());
	}

	public static ResourceDto convertEntityToDto(Resource resource) {
		return new ResourceDto(resource.getResourceId(), resource.getResourceCode(), resource.getResourceName(),
				resource.getResourceType(), resource.getStartDate(), resource.getEndDate(), resource.getAvailability(),
				resource.getUserId(), resource.getWorkOrderCode(), resource.getInventoryId());

	}

//	public static ResourceRespDto convertEntityToDto1(Resource resource) {
//		return new ResourceRespDto(resource.getId(), resource.getEmployeeName(), 0, resource.getProductName(),
//				resource.getPhoneNumber(), resource.getLocation(), resource.getAssignedBy());
//	}

//	public static Asset convertDtoToNewEntity(AssetDto assetDto, Location location, String createdBy) {
//  long createdTime = System.currentTimeMillis();
//  return new Asset(assetDto.getId(), assetDto.getName(), assetDto.getDescription(),
//          assetDto.getPrice(), location, createdBy, createdTime, createdBy, createdTime,false);
//  
//????????}
	public static WorkOrder convertDtoToNewEntity(WorkOrderDto workorderDto, Employee employee, String createdBy,
			MultipartFile file) throws IOException {
		long createdTime = System.currentTimeMillis();
		var data = ImageUtils.compressImage(file.getBytes());
		var name = file.getOriginalFilename();
		return new WorkOrder(workorderDto.getOrderNo(), workorderDto.getWorkOrderCode(), workorderDto.getStatus(),
				workorderDto.getName(), workorderDto.getEmailId(), employee, workorderDto.getPhoneNumber(),
				workorderDto.getDescription(), workorderDto.getWorkSubject(), workorderDto.getTaskDetails(),
				workorderDto.getDate(), workorderDto.getWorkOrderCost(), data, name, createdBy, createdTime, createdBy,
				createdTime, 0, workorderDto.getExpectedCompletionTime(), false);
	}

	public static WorkOrderRespDto convertEntityToRespDto(WorkOrder workorder) {

		byte[] images = ImageUtils.decompressImage(workorder.getData());
		return new WorkOrderRespDto(workorder.getOrderNo(), workorder.getCode(), workorder.getStatus(),
				workorder.getName(), workorder.getEmailId(), workorder.getEmployeeId(), workorder.getPhoneNumber(),
				workorder.getDescription(), workorder.getWorkSubject(), workorder.getTaskDetails(), workorder.getDate(),
				workorder.getWorkOrderCost(), images, workorder.getFileName(), workorder.getCreatedBy(),
				workorder.getCreatedTime(), workorder.getUpdatedBy(), workorder.getUpdatedTime(),
				workorder.getTimeTaken(), false, workorder.getExpectedCompletionTime());
	}

	public static WorkOrder convertDtoToUpdateEntity(WorkOrderDto workorderDto, Employee employee, String updatedBy,MultipartFile file, WorkOrder workOrder) throws IOException{
			
			long updatedTime = System.currentTimeMillis();
			var data = ImageUtils.compressImage(file.getBytes());
		    var name =file.getOriginalFilename();
			long createdTime = workOrder.getCreatedTime();
			String createdBy = workOrder.getCreatedBy();
			return new WorkOrder(workorderDto.getOrderNo(),workorderDto.getWorkOrderCode(), workorderDto.getStatus(),workorderDto.getName(),
	        		workorderDto.getEmailId(),employee,workorderDto.getPhoneNumber(),
	                workorderDto.getDescription(),workorderDto.getWorkSubject(),workorderDto.getTaskDetails(),workorderDto.getDate(),workorderDto.getWorkOrderCost(),
	                data,name,createdBy, createdTime, updatedBy, updatedTime,0,workorderDto.getExpectedCompletionTime(), false);
			}

	public static Inventory convertDtoToNewEntity(InventoryDto inventoryDto, String createdBy, MultipartFile file)
			throws IOException {
		long createdTime = System.currentTimeMillis();
		var data = ImageUtils.compressImage(file.getBytes());
		var name = file.getOriginalFilename();
		return new Inventory(inventoryDto.getId(), inventoryDto.getName(), inventoryDto.getCode(),
				inventoryDto.getDescription(), inventoryDto.getQuantity(), inventoryDto.getPrice(),
				inventoryDto.getStatus(), data, name, createdBy, createdTime, createdBy, createdTime, false);
	}

	public static Inventory convertDtoToUpdateEntity(InventoryDto inventoryDto, String updatedBy, Inventory inventory) {
		long updatedTime = System.currentTimeMillis();
		long createdTime = inventory.getCreatedTime();
		String createdBy = inventory.getCreatedBy();
		return new Inventory(inventoryDto.getId(), inventoryDto.getName(), inventoryDto.getCode(),
				inventoryDto.getDescription(), inventoryDto.getQuantity(), inventoryDto.getPrice(),
				inventoryDto.getStatus(), inventoryDto.getData(), inventoryDto.getFileName(), createdBy, createdTime,
				updatedBy, updatedTime, false);
	}

	public static Locations convertDtoToNewEntity(LocationsDto locationDto, String createdBy) {
		long createdTime = System.currentTimeMillis();
		return new Locations(locationDto.getId(), locationDto.getFacCode(), locationDto.getName(),
				locationDto.getDescription(), locationDto.getAddressLine1(), locationDto.getAddressLine2(),
				locationDto.getAddressLine3(), locationDto.getCity(), locationDto.getState(),
				locationDto.getPostalCode(), locationDto.getCountry(), createdBy, createdTime, createdBy, createdTime,
				false);
	}

	public static Locations convertDtoToUpdateEntity(LocationsDto locationDto, String updatedBy, Locations location) {
		long updatedTime = System.currentTimeMillis();
		long createdTime = location.getCreatedTime();
		String createdBy = location.getCreatedBy();
		return new Locations(locationDto.getId(), locationDto.getFacCode(), locationDto.getName(),
				locationDto.getDescription(), locationDto.getAddressLine1(), locationDto.getAddressLine2(),
				locationDto.getAddressLine3(), locationDto.getCity(), locationDto.getState(),
				locationDto.getPostalCode(), locationDto.getCountry(), createdBy, createdTime, updatedBy, updatedTime,
				false);
	}

	public static String convertTimestampToWeek(long timestamp) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		int weekNo = cal.get(Calendar.WEEK_OF_MONTH);
		int mont = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(mont) + "-" + String.valueOf(year) + "(" + String.valueOf(weekNo) + ")";
	}

	public static String convertTimestampToWeekNo(long timestamp) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		int weekNo = cal.get(Calendar.WEEK_OF_MONTH);
		return String.valueOf(weekNo);

	}

	public static String convertTimestampToMonth(long timestamp) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		int mont = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(mont) + "-" + String.valueOf(year);
	}

	public static LookUps convertDtoToNewEntity(LookUpsDto lookUpsDto, String name) {
		long createdTime = System.currentTimeMillis();
		return new LookUps(lookUpsDto.getId(), lookUpsDto.getName(), lookUpsDto.getValues(), name, createdTime, name, createdTime, false);
	}
	
	public static LookUps convertDtoToUpdateEntity(LookUps lookUps,LookUpsDto lookUpsDto, String name) {
		long updateTime = System.currentTimeMillis();
		return new LookUps(lookUps.getId(), lookUpsDto.getName(), lookUpsDto.getValues(), lookUps.getCreatedBy(), lookUps.getCreatedTime(), name, updateTime, false);
	}
}
