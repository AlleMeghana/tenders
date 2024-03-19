package com.tenders.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class DdDto {

	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate issueDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate clearDate;
	private String bankName;
	
}
