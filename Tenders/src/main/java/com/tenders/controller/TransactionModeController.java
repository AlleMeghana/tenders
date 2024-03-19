package com.tenders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.TransactionDetailsDto;
import com.tenders.service.TransactionModeService;

@RestController
@RequestMapping("/transaction")
public class TransactionModeController {

	@Autowired
	private TransactionModeService transactionModeService;

	@PostMapping("/save-txn")
	public ApiResponse saveTransactionDetails(@RequestBody TransactionDetailsDto txnDetailsDto) {
		return transactionModeService.saveTransactionDetails(txnDetailsDto);

	}

}
