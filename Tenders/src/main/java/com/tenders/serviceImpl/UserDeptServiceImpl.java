package com.tenders.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenders.Repository.UserDeptRepository;
import com.tenders.dto.ApiResponse;
import com.tenders.dto.UserDeptDto;
import com.tenders.entity.UserDept;
import com.tenders.service.UserDeptService;
@Service
public class UserDeptServiceImpl implements UserDeptService {
	
	@Autowired
	private UserDeptRepository userdeptRepo;


	@Override
	public ApiResponse createUserDept(UserDeptDto userDeptDto) {
		
		UserDept userdept = new UserDept();
		userdept.setId(userDeptDto.getId());
		userdept.setDeptName(userDeptDto.getDeptName());
		userdept.setAddress(userDeptDto.getAddress());
		userdept.setCountry(userDeptDto.getCountry());
		userdept.setState(userDeptDto.getState());
		userdeptRepo.save(userdept);
		return new ApiResponse("Inserted successfully " + userdept, HttpStatus.OK);

	}

	@Override
	public ApiResponse getUserdept() {
		List<UserDept> userdeptList = userdeptRepo.findAll();
		if(!userdeptList.isEmpty()) {
		List<UserDeptDto> userdeptDtoList=new ArrayList	<>();
		for(UserDept userdept:userdeptList ) {
			UserDeptDto userdto=new UserDeptDto();
			userdto.setId(userdept.getId());
			userdto.setDeptName(userdept.getDeptName());
			userdto.setAddress(userdept.getAddress());
			userdto.setCountry(userdept.getCountry());
			userdto.setState(userdept.getState());
		
		userdeptDtoList.add(userdto);
		}
		
		return new ApiResponse(userdeptList, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found", HttpStatus.BAD_REQUEST);
		}

	}
	@Override
	public ApiResponse updateUserDept(Long id, UserDeptDto userDeptDto) {

		Optional<UserDept> userDeptOptional = userdeptRepo.findById(id);
		UserDept userDept = userDeptOptional.get();
		if (userDeptOptional.isPresent()) {
			userDept.setDeptName(userDeptDto.getDeptName());
			userDept.setAddress(userDeptDto.getAddress());
			userDept.setCountry(userDeptDto.getCountry());
			userDept.setState(userDeptDto.getState());
			userdeptRepo.save(userDept);
			return new ApiResponse("Data updated successfully ", HttpStatus.OK);
		} else {
			return new ApiResponse("No data found to update ", HttpStatus.BAD_REQUEST);
		}
	}

}
