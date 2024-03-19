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

public class CheckDto {
	
	private Long id;
	private String bankName;
	private String chequeNumber;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIssueDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkCreateDate;

}
