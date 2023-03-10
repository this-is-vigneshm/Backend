package com.staunch.tech.controller;

import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.EmployeeDto;
import com.staunch.tech.dto.EmployeeUpdateReqDto;
import com.staunch.tech.service.IEmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> addAsset( @RequestBody EmployeeDto employeeDto) {
        var response = new ApiResponseDto("1200","Success", employeeService.registerEmployee(employeeDto));
        return new ResponseEntity<>(response,
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponseDto> listAllEmployees() {
        var response = new ApiResponseDto("1200","Success", employeeService.getAllEmployees());
        return new ResponseEntity<>(response,
                HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<ApiResponseDto> getEmployeeById(
            @PathVariable("empId") int empId) {
        var response = new ApiResponseDto("1200","Success", employeeService.getEmployeeById(empId));
        return new ResponseEntity<>(response,
                HttpStatus.OK);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<ApiResponseDto> updateEmployee(
            @PathVariable("empId") int empId,
            @RequestBody EmployeeUpdateReqDto employeeDto) {
        var response = new ApiResponseDto("1200","Success",employeeService.updateEmployee(empId, employeeDto));
        return new ResponseEntity<>(response,
                HttpStatus.ACCEPTED);
    }
    @GetMapping("/usertype/{usertype}")
	public ResponseEntity<ApiResponseDto> getAllEmployeesByUserType(@PathVariable("usertype") String usertype) {
		var response = new ApiResponseDto("1200", "Success", employeeService.getAllEmployeesByUserType(usertype));
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
    
    @PutMapping("/status/{empId}")
	public ResponseEntity<ApiResponseDto> updateEmployeeStatus(@PathVariable("empId") int empId,
			@RequestBody EmployeeUpdateReqDto updateEmployeeStatusDto) {
		var response = new ApiResponseDto("1200", "Success",
				employeeService.updateEmployeeStatus(empId, updateEmployeeStatusDto));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
    
    @PostMapping("/saveAll")
    public ResponseEntity<ApiResponseDto> addEmployee(@RequestBody List<EmployeeDto> multireg) {
        var response = new ApiResponseDto("1200","Success", employeeService.registerAllEmployee(multireg));
        return new ResponseEntity<>(response,
                HttpStatus.ACCEPTED);
    }
    
}
