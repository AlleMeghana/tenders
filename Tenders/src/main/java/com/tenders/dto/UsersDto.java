package com.tenders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

	private Long id;
	private String userName;
	private String password;
	private String email;
	private String phoneNumber;
	private String fullName;
	private String roles;
	private Boolean isFirstLogin;
	private String lastPasswordUpdate;

}
