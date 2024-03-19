package com.tenders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.BomDto;
import com.tenders.service.BomService;
@RestController
@RequestMapping("/bom")

public class BomController {
	
	@Autowired
	BomService bomservice;
	@PatchMapping("/editbom/{id}")
	public ApiResponse editbom(@PathVariable("id") Long id, @RequestBody BomDto bomDto) {
		return bomservice.updateBOM(id, bomDto);

	}
	
	@GetMapping("/getbom")
	public ApiResponse getBomById() {
		return bomservice.getBom();

	}
	
//	@DeleteMapping("/deletebom/{id}")
//    public ResponseEntity<String> deleteEntityById(@PathVariable("id") Long id) {
//        try {
//        	bomservice.deleteBomById(id);
//            return new ResponseEntity<>("Entity with ID " + id + " deleted successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to delete entity with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}