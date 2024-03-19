package com.tenders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDeptDto {
	
	private Long id;
	private String deptName;
	private String address;
	private String state;
	private String country;

}
