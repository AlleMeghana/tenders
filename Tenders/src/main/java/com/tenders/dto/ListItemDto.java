package com.tenders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListItemDto {

	private Long id;
	private String listName;
	private String listItem;
	private String status;
	
	
}
