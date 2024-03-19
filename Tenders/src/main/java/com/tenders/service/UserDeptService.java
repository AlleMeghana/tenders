package com.tenders.service;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.UserDeptDto;

public interface UserDeptService {
	public ApiResponse createUserDept(UserDeptDto userDeptDto);

	public ApiResponse getUserdept();

	public ApiResponse updateUserDept(Long id, UserDeptDto userDeptDto);


}