package com.tenders.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tenders.Repository.UsersRepository;
import com.tenders.dto.ApiResponse;
import com.tenders.dto.UsersDto;
import com.tenders.entity.UsersEntity;
import com.tenders.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


		@Override
		public ApiResponse saveUsers(UsersDto usersDto) {

			if (usersDto != null) {
				UsersEntity usersEntity = new UsersEntity();
				usersEntity.setUserName(usersDto.getUserName());
				usersEntity.setPassword(passwordEncoder.encode(usersDto.getPassword()));
				usersEntity.setEmail(usersDto.getEmail());
				usersEntity.setFullName(usersDto.getFullName());
				usersEntity.setId(usersDto.getId());
				usersEntity.setIsFirstLogin(usersDto.getIsFirstLogin());
				usersEntity.setLastPasswordUpdate(usersDto.getLastPasswordUpdate());
				usersEntity.setPhoneNumber(usersDto.getPhoneNumber());
				usersEntity.setRoles(usersDto.getRoles());

				usersRepository.save(usersEntity);

				return new ApiResponse("Inserted successfully " + usersEntity, HttpStatus.OK);
			} else {
				return new ApiResponse("No data found to insert ", HttpStatus.BAD_REQUEST);
			}
		}
	}