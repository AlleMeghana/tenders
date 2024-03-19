package com.tenders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.ListItemDto;
import com.tenders.service.ListItemService;

@CrossOrigin("*")
@RestController
@RequestMapping("/list")
public class ListItemController {

	@Autowired
	private ListItemService listItemService;

	@GetMapping("/system-list")
	public ApiResponse getSystemList() {
		return listItemService.getSytemList();

	}

	@GetMapping("/item-list/{listName}")
	public ApiResponse getItemList(@PathVariable("listName") String listName) {
		return listItemService.getListItems(listName);

	}

	@PostMapping("/save-list/{name}")
	public ApiResponse saveSystemListItems(@PathVariable("name") String name,@RequestBody ListItemDto listItemDto) {
		return listItemService.saveSystemListItems(name, listItemDto);

	}

	@PatchMapping("/update/{id}")
	public ApiResponse updateSystemListItems(@PathVariable("id") Long id,@RequestBody ListItemDto listItemDto) {	
		return listItemService.updateSystemListItems(id, listItemDto);

	}
	@GetMapping("/system/{systemId}")
	public ApiResponse getSystemItemListById(@PathVariable("systemId") Long systemId) {
		return listItemService.getListItemsBySystemId(systemId);

	}

}
