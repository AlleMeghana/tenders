package com.tenders.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {
	
	private Long empid;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate doj;
	private String designation;
	private byte[] imageData;
	
}