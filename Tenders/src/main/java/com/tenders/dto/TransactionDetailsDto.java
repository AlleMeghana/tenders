package com.tenders.dto;

import java.time.LocalDate;
import java.util.List;import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDetailsDto {
	
	private Long id;
	private String title;
	private String description;
	private LocalDate transactionDate;
	private String transactionType;
	private ModeOfTransactionDto modeOfTransaction;
	private String transactionFor;
	private String recievedFrom;
	private String paidTo;

	private List<TransactionModeDetailsDto> txnModeDeatilsDto;
}
