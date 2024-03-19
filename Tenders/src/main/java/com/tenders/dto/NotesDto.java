package com.tenders.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class NotesDto {
	
	private Long id;
	private String loggedBy;
	private LocalDate loggedTime;
	private String note;
	//private TendersDto tendersDto;

}
