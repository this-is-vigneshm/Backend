package com.staunch.tech.service.impl;

import com.staunch.tech.dto.EmployeeDto;
import com.staunch.tech.dto.EmployeeRespDto;
import com.staunch.tech.dto.EmployeeUpdateReqDto;
import com.staunch.tech.entity.Employee;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.service.IEmployeeService;
import com.staunch.tech.utils.ConversionUtils;
import com.staunch.tech.utils.ValidationUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	private ValidationUtils validationUtils;

	static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * @param employeeDto
	 * @return
	 */
	@Override
	public EmployeeRespDto registerEmployee(EmployeeDto employeeDto) {
		try {
			validationUtils.validate(employeeDto);
			var employee = ConversionUtils.convertDtoToNewEntity(employeeDto, "Dhinesh");
			var encodedPassword = passwordEncoder.encode(employee.getPassword());
			employee.setPassword(encodedPassword);
			var savedEmployee = employeeRepository.save(employee);
			return ConversionUtils.convertEntityToDto(savedEmployee);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}

	/**
	 * @param employeeDto
	 * @return
	 */
	@Override
	public EmployeeRespDto updateEmployee(int empId, EmployeeUpdateReqDto employeeDto) {
		try {
			if (empId != employeeDto.getId()) {
				throw new AssetManagementException("Employee id in body and path doesn't match");
			}
			var employeeOpt = employeeRepository.findById(employeeDto.getId());
			if (employeeOpt.isEmpty()) {
				throw new AssetManagementException("Employee id is Invalid");
			}
			var userOpt = employeeRepository.findById(employeeDto.getUserId());
			if (userOpt.isEmpty()) {
				throw new AssetManagementException("User Id is Invalid!");
			}
			var employee = ConversionUtils.convertDtoToUpdateEntity(employeeDto, userOpt.get().getName(),
					employeeOpt.get());
			var savedEmployee = employeeRepository.save(employee);
			return ConversionUtils.convertEntityToDto(savedEmployee);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}

	@Override
	public EmployeeRespDto updateEmployeeStatus(int id, EmployeeUpdateReqDto employeeUpdateReqDto) {
		var employeeOpt = employeeRepository.findById(id);
		if (employeeOpt.isEmpty()) {
			throw new AssetManagementException("Employee Id is Invalid");
		}
		var userOpt = employeeRepository.findById(employeeUpdateReqDto.getUserId());
		if (userOpt.isEmpty()) {
			throw new AssetManagementException("User Id is Invalid!");
		}
		var employee = employeeOpt.get();
		employee.setStatus(employeeUpdateReqDto.getStatus());
		System.out.print(employee.getStatus());
		return ConversionUtils.convertEntityToDto(employeeRepository.save(employee));
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public EmployeeRespDto getEmployeeById(int id) {
		var employeeOpt = employeeRepository.findById(id);
		if (employeeOpt.isEmpty()) {
			throw new AssetManagementException("Employee id is Invalid");
		}
		return ConversionUtils.convertEntityToDto(employeeOpt.get());
	}

	/**
	 * @return
	 */
	@Override
	public List<EmployeeRespDto> getAllEmployees() {
		var employeeList = employeeRepository.findAll();
		if (employeeList.isEmpty()) {
			throw new AssetManagementException("Employee List is Empty");
		}
		var employeeDtoList = new ArrayList<EmployeeRespDto>();
		for (Employee emp : employeeList) {
			employeeDtoList.add(ConversionUtils.convertEntityToDto(emp));
		}
		return employeeDtoList;
	}

	@Override
	public List<EmployeeRespDto> getAllEmployeesByStatus(String status) {
		var employeeList = employeeRepository.findByStatus(status);
		if (employeeList.isEmpty()) {
			throw new AssetManagementException("Ticket List is Empty");
		}
		var employeeDtoList = new ArrayList<EmployeeRespDto>();
		for (Employee emp : employeeList) {
			employeeDtoList.add(ConversionUtils.convertEntityToDto(emp));
		}
		return employeeDtoList;
	}

	public String deleteEmployee(int id) {
		var employeeOpt = employeeRepository.findById(id);
		if (employeeOpt.isEmpty()) {
			throw new AssetManagementException("Employee id is Invalid");
		}
		employeeRepository.deleteById(id);
		return "Employee with id : " + id + " deleted successfully";
	}
	@Override
	public List<EmployeeRespDto> getAllEmployeesByUserType(String usertype) {
		var employeeList = employeeRepository.findByUsertype(usertype);
		if (employeeList.isEmpty()) {
			throw new AssetManagementException("Ticket List is Empty");
		}
		var employeeDtoList = new ArrayList<EmployeeRespDto>();
		for (Employee emp : employeeList) {
			employeeDtoList.add(ConversionUtils.convertEntityToDto(emp));
		}
		return employeeDtoList;
	}
	
	public String registerAllEmployee(List<EmployeeDto> multireg){
		
		for(var i : multireg) {
			employeeRepository.save( ConversionUtils.convertDtoToNewEntity(i, "Srinath"));
		}
		return "SUCCESS";
	}

}


//	/**
//	 * @param username
//	 * @return
//	 * @throws UsernameNotFoundException
//	 */
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		var employeeOpt = employeeRepository.findByUsername(username);
//		if (employeeOpt.isEmpty()) {
//			throw new UsernameNotFoundException("Username Not Valid");
//		}
//		return new User(username, employeeOpt.get().getPassword(), employeeOpt.get().getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
//	}
//}