package com.tenders.service;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.EmployeeDto;

public interface EmployeeService {

	public ApiResponse saveEmployees(EmployeeDto employeeDto);

	public ApiResponse getEmployees();

	public ApiResponse updateEmployees(Long id, EmployeeDto employeeDto);

}