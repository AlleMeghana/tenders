package com.tenders.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenders.Repository.NoteRepository;
import com.tenders.Repository.TendersRepository;
import com.tenders.Repository.UsersRepository;
import com.tenders.dto.ApiResponse;
import com.tenders.dto.NotesDto;
import com.tenders.entity.Notes;
import com.tenders.entity.Tenders;
import com.tenders.entity.UsersEntity;
import com.tenders.service.NotesService;
@Service
public class NotesServiceImpl implements NotesService {

	@Autowired
	TendersRepository tendersRepo;
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private NoteRepository noteRepository;


	@Override
	public ApiResponse saveNotes(String userName, String tendernum, NotesDto notesDto) {

		Optional<Tenders> tendersopt = tendersRepo.findByTenderNum(tendernum);
		Tenders tenders = tendersopt.get();

		Optional<UsersEntity> userList = userRepo.findByUserName(userName);
		UsersEntity userEntity = userList.get();

		if (notesDto != null) {
			Notes notes = new Notes();
//		if(tenders.getNotes()==null|| tenders.getNotes().isEmpty()) {
			notes.setId(notesDto.getId());
			notes.setLoggedBy(userEntity.getUserName());
			notes.setNote(notesDto.getNote());
			notes.setTenders(tenders);

			notes.setUser(userEntity);
			noteRepository.save(notes);

			return new ApiResponse("Data inserted successfully " + notes, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found to update ", HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ApiResponse getNotesBytenderNumber(Long id) {

		Optional<Tenders> tenderList = tendersRepo.findById(id);

		List<NotesDto> notesDtoList = new ArrayList<>();
		if (tenderList.isPresent()) {
			Tenders tenders = tenderList.get();
			for (Notes notes : tenders.getNotes()) {
				NotesDto notesDto = new NotesDto();
				notesDto.setId(notes.getId());
				notesDto.setLoggedBy(notes.getLoggedBy());
				notesDto.setLoggedTime(notes.getLoggedTime());
				notesDto.setNote(notes.getNote());

				notesDtoList.add(notesDto);
			}
			return new ApiResponse(notesDtoList, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found", HttpStatus.BAD_REQUEST);
		}

	}


}
