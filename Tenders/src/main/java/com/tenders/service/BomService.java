package com.tenders.service;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.BomDto;

public interface BomService {
	public ApiResponse updateBOM(Long id, BomDto bomDto);

	public ApiResponse getBom();
//	public void deleteBomById(Long id);

}