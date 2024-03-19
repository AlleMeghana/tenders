package com.tenders.service;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.TransactionDetailsDto;

public interface TransactionModeService {
	
	public ApiResponse saveTransactionDetails(TransactionDetailsDto txnDetailsDto);
	
	

}
