package com.tenders.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenders.Repository.ListItemRepository;
import com.tenders.Repository.SystemListRepository;
import com.tenders.dto.ApiResponse;
import com.tenders.dto.ListItemDto;
import com.tenders.dto.SystemListDto;
import com.tenders.entity.ListItems;
import com.tenders.entity.SystemList;
import com.tenders.service.ListItemService;

@Service
public class ListItemSeviceImpl implements ListItemService {

	@Autowired
	private SystemListRepository systemListRepo;

	@Autowired
	private ListItemRepository listItemRepo;

	@Override
	public ApiResponse getSytemList() {
		List<SystemList> systemList = systemListRepo.findAll();

		List<SystemListDto> systemListDto = new ArrayList<>();
		if (!systemList.isEmpty()) {
			for (SystemList sytemL : systemList) {
				SystemListDto systemLDto = new SystemListDto();
				systemLDto.setName(sytemL.getName());
				systemLDto.setStatus(sytemL.getStatus());
				systemListDto.add(systemLDto);
			}
		}

		return new ApiResponse(systemListDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse getListItems(String listName) {

		List<ListItems> listItemList = new ArrayList<>();

		listItemList = listItemRepo.findByListName(listName);

		List<ListItemDto> itemListDto = new ArrayList<ListItemDto>();
		for (ListItems listItems : listItemList) {
			ListItemDto listItemsDto = new ListItemDto();
			listItemsDto.setId(listItems.getId());
			listItemsDto.setListItem(listItems.getListItem());
			listItemsDto.setListName(listItems.getListName());
			listItemsDto.setStatus(listItems.getStatus());
			itemListDto.add(listItemsDto);
		}

		return new ApiResponse(itemListDto, HttpStatus.OK);

//		List<SystemList> systemList = new ArrayList<SystemList>();
//		List<SystemListDto> sysListDtoList = new ArrayList<SystemListDto>();
//
//		systemList = systemListRepo.findByName(name);
////		for (SystemList sysList : systemList) {
////			SystemListDto sysListDto = new SystemListDto();
////			sysListDto.setName(sysList.getName());
////			sysListDto.setStatus(sysList.getStatus());
//
//			List<ListItemDto> listItemDtoList = new ArrayList<ListItemDto>();
//			for (ListItems listItems : sysList.getListItem()) {
//				ListItemDto listItemDto = new ListItemDto();
//				listItemDto.setListItem(listItems.getListItem());
//				listItemDto.setListName(listItems.getListName());
//				listItemDto.setStatus(listItems.getStatus());
//				listItemDtoList.add(listItemDto);
//			}
//			sysListDto.setListItemDto(listItemDtoList);
//			sysListDtoList.add(sysListDto);
//
//		}
//
//		return new ApiResponse(sysListDtoList, HttpStatus.OK);
//	}
	}

	@Override
	public ApiResponse saveSystemListItems(String name, ListItemDto listItemDto) {

		List<SystemList> systemListL = systemListRepo.findByName(name);

		for (SystemList systemList : systemListL) {
			ListItems newListItem = new ListItems();
			newListItem.setListItem(listItemDto.getListItem());
			newListItem.setListName(listItemDto.getListName());
			newListItem.setStatus(listItemDto.getStatus());
			newListItem.setSystemList(systemList);

			List<ListItems> listItemList = systemList.getListItem();
			if (listItemList == null) {
				listItemList = new ArrayList<>();
			}
			listItemList.add(newListItem);
			systemList.setListItem(listItemList);
			systemListRepo.save(systemList);
		}

		return new ApiResponse(listItemDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse updateSystemListItems(Long id, ListItemDto listItemDto) {

		Optional<ListItems> listItemsList = listItemRepo.findById(id);
		ListItems listItems = listItemsList.get();

		listItems.setListItem(listItemDto.getListItem());
		listItems.setListName(listItemDto.getListName());
		listItems.setStatus(listItemDto.getStatus());

		listItemRepo.save(listItems);

		return new ApiResponse("Data updated successfully" + listItems, HttpStatus.OK);
	}
	@Override
	public ApiResponse getListItemsBySystemId(Long systemId) {

		List<ListItems> listItemList = new ArrayList<>();

		listItemList = listItemRepo.findBySystemListId(systemId);

		List<ListItemDto> itemListDto = new ArrayList<ListItemDto>();
		for (ListItems listItems : listItemList) {
			ListItemDto listItemsDto = new ListItemDto();
			listItemsDto.setId(listItems.getId());
			listItemsDto.setListItem(listItems.getListItem());
			listItemsDto.setListName(listItems.getListName());
			listItemsDto.setStatus(listItems.getStatus());
			itemListDto.add(listItemsDto);
		}
		return new ApiResponse(itemListDto, HttpStatus.OK);

	}
}
