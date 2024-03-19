package com.tenders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BomDto {

	private Long id;
	private String item;
	private String description;
	private Integer quantity;
	

}
