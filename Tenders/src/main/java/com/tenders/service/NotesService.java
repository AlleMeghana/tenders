package com.tenders.service;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.NotesDto;

public interface NotesService {
public ApiResponse saveNotes(String userName,String tendernum,NotesDto notesDto);
	
	public ApiResponse getNotesBytenderNumber(Long id);
	

}