package com.tenders.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenders.Repository.EmployeeRepo;
import com.tenders.dto.ApiResponse;
import com.tenders.dto.EmployeeDto;
import com.tenders.entity.Employee;
import com.tenders.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public ApiResponse saveEmployees(EmployeeDto employeeDto) {
		Employee emp = new Employee();
		emp.setName(employeeDto.getName());
		emp.setDoj(employeeDto.getDoj());
		emp.setDesignation(employeeDto.getDesignation());
		emp.setImageData(employeeDto.getImageData());
		employeeRepo.save(emp);
		return new ApiResponse("Inserted successfully " + emp, HttpStatus.OK);

	}

	@Override
	public ApiResponse getEmployees() {
		List<Employee> empList = employeeRepo.findAll();
		if (!empList.isEmpty()) {
			List<EmployeeDto> empDtoList = new ArrayList<>();
			for (Employee emp : empList) {
				EmployeeDto empDto = new EmployeeDto();
				empDto.setEmpid(emp.getEmpid());
				empDto.setName(emp.getName());
				empDto.setDoj(emp.getDoj());
				empDto.setDesignation(emp.getDesignation());
				empDto.setImageData(emp.getImageData());
				empDtoList.add(empDto);
			}

			return new ApiResponse(empDtoList, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ApiResponse updateEmployees(Long id, EmployeeDto employeeDto) {
		Optional<Employee> empOptional = employeeRepo.findById(id);
		Employee emp = empOptional.get();
		if (empOptional.isPresent()) {
			// emp.setEmpid(employeeDto.getEmpid());
			emp.setName(employeeDto.getName());
			emp.setDesignation(employeeDto.getDesignation());
			emp.setDoj(employeeDto.getDoj());
			emp.setImageData(employeeDto.getImageData());
			employeeRepo.save(emp);

			return new ApiResponse("Data updated successfully ", HttpStatus.OK);
		} else {
			return new ApiResponse("No data found to update ", HttpStatus.BAD_REQUEST);
		}
	}
}