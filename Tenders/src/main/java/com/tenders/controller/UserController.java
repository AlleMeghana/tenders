package com.tenders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.UsersDto;
import com.tenders.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@RestController
@RequestMapping("/users")

public class UserController {
	
	@Autowired
	UserService userService;
	@PostMapping("/user-details")
	public ApiResponse saveUser(@RequestBody UsersDto usersDto) {
		return userService.saveUsers(usersDto);

	}
	@GetMapping("/logout")
    public String getLogoutPage(HttpServletRequest request, HttpServletResponse response){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null){
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
        return "logout successful";
//    }
//	
	}



}
