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

import com.tenders.dto.ApiResponse;
import com.tenders.dto.UserDeptDto;
import com.tenders.service.UserDeptService;
@CrossOrigin("*")
@RestController
@RequestMapping("/userDept")

public class UserDeptController {
	
	@Autowired
	UserDeptService userDeptService;
	@PostMapping("/save-userdept")
	public ApiResponse saveUserdept(@RequestBody UserDeptDto userDto) {
		return userDeptService.createUserDept(userDto);

	}
	@GetMapping("/get-userdept")
	public ApiResponse getUserDept() {
		return userDeptService.getUserdept();

	}
	@PatchMapping("/update/{id}")
	public ApiResponse updateUserDept(@PathVariable("id") Long id, @RequestBody UserDeptDto userDeptDto) {
		return userDeptService.updateUserDept(id, userDeptDto);

	}

}