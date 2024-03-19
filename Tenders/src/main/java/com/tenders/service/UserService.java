package com.tenders.service;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.UsersDto;

public interface UserService {
	public ApiResponse saveUsers(UsersDto usersDto);

}