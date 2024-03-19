package com.tenders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.EmployeeDto;
import com.tenders.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/employees")

public class EmployeeController {
	@Autowired
	EmployeeService empService;
	
	@PostMapping("/save-emp")
	public ApiResponse saveEmp(@RequestBody EmployeeDto empDto) {
		return empService.saveEmployees(empDto);

	}
	@GetMapping("/get-emp")
	public ApiResponse getEmp() {
		return empService.getEmployees();

	}
	
	@PatchMapping("/update/{id}")
	public ApiResponse updateTenders(@PathVariable("id") Long id, @RequestBody EmployeeDto empDto,MultipartFile file) {
		return empService.updateEmployees(id, empDto);

	}




}
