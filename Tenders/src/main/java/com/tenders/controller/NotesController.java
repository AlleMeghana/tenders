package com.tenders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.NotesDto;
import com.tenders.service.NotesService;

@RestController
@RequestMapping("/notes")

public class NotesController {
	
	@Autowired
	NotesService notesService;
	
	
	@PostMapping("notes/{userName}/{tendernum}")
	public ApiResponse saveNotes(@PathVariable("userName") String userName, @PathVariable("tendernum") String tendernum,
			@RequestBody NotesDto notesDto) {
		try {
			return notesService.saveNotes(userName, tendernum, notesDto);
		} catch (StackOverflowError e) {
			e.printStackTrace(); // Log the stack trace to identify the cause
			return new ApiResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/note/{id}")
	public ApiResponse getNotesBytenderNumber(@PathVariable("id") Long id) {
		return notesService.getNotesBytenderNumber(id);

	}

}
