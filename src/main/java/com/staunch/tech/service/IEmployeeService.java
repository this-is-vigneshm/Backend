package com.staunch.tech.service;

import com.staunch.tech.dto.EmployeeDto;
import com.staunch.tech.dto.EmployeeRespDto;
import com.staunch.tech.dto.EmployeeUpdateReqDto;
import com.staunch.tech.dto.TicketRespDto;
import com.staunch.tech.dto.UpdateTicketStatusDto;
import com.staunch.tech.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
	EmployeeRespDto registerEmployee(EmployeeDto employeeDto);

	EmployeeRespDto updateEmployee(int empId, EmployeeUpdateReqDto employeeDto);
	EmployeeRespDto updateEmployeeStatus(int id,EmployeeUpdateReqDto employeeUpdateReqDto);
	EmployeeRespDto getEmployeeById(int id);

	List<EmployeeRespDto> getAllEmployees();
    
	String deleteEmployee(int id);

	List<EmployeeRespDto> getAllEmployeesByStatus(String status);
	List<EmployeeRespDto> getAllEmployeesByUserType(String usertype);
	

	

}