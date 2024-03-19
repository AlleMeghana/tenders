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

public class TransactionModeDetailsDto {

	public Long id;
	public String bankname;
	public String transactionNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIssueDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkClearDate;
	public String checkNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dDIssueDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate ddClearDate;

}
