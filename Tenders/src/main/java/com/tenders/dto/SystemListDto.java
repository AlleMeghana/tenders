package com.tenders.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SystemListDto {

	private Long id;
	private String name;
	private String status;
	private List<ListItemDto> listItemDto;
}
