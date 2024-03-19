package com.tenders.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ModeOfTransactionDto {
	
	private Long id;
	private String modeOfTransaction;

	private List<DdDto> ddDto;
	private List<CheckDto> checkDto;
	private List<OnlineDto> onlineDto;
}
