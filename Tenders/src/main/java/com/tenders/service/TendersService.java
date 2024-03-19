package com.tenders.service;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.TendersDto;

public interface TendersService {

	public ApiResponse saveTenders(TendersDto tendersDto);

	public ApiResponse getTenders();

//	public ApiResponse saveUsers(UsersDto usersDto);

	public ApiResponse updateTenders(Long id, TendersDto tendersDto);

//	public ApiResponse saveNotes(String userName, String tendernum, NotesDto notesDto);
//
//	public ApiResponse getNotesBytenderNumber(Long id);

	public ApiResponse getBOMDescTenNum(Long id);

	public ApiResponse getStates();

	public ApiResponse getVerticals();

	public ApiResponse getAssignedTo();

	public ApiResponse getTenderStatus();
	
	public ApiResponse fetchDataExcludingStaticValues();
	
	
	//for DashBoard
	public ApiResponse fetchVerticals(String verticals);
	
	public ApiResponse fetchByStates(String states);
	
	public ApiResponse fetchByVerticalsAndStates(String states ,String verticals);

	public ApiResponse getTendersByVerticals(String verticals);

	public ApiResponse getTendersByStates(String states);

	public ApiResponse getTendersByStatesAndVerticals(String states, String verticals);

	
//	public ApiResponse createUserDept(UserDeptDto userDeptDto);
//
//	public ApiResponse getUserdept();
//
//	public ApiResponse updateBOM(Long id, BomDto bomDto);

//	public ApiResponse getBom();

}
