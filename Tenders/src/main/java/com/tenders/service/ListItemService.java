package com.tenders.service;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.ListItemDto;

public interface ListItemService {
	
	public ApiResponse getSytemList();
	
	public ApiResponse getListItems(String name);
	
	public ApiResponse updateSystemListItems(Long id,ListItemDto listItemDto);

	ApiResponse saveSystemListItems(String name, ListItemDto listItemDto);

	ApiResponse getListItemsBySystemId(Long systemId);

}
