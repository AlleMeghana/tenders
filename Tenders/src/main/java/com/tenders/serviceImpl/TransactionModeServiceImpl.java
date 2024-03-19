package com.tenders.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenders.Repository.TransactionDetailsRepository;
import com.tenders.dto.ApiResponse;
import com.tenders.dto.CheckDto;
import com.tenders.dto.DdDto;
import com.tenders.dto.ModeOfTransactionDto;
import com.tenders.dto.OnlineDto;
import com.tenders.dto.TransactionDetailsDto;
import com.tenders.dto.TransactionModeDetailsDto;
import com.tenders.entity.CheckEntity;
import com.tenders.entity.Dd;
import com.tenders.entity.ModeOfTransaction;
import com.tenders.entity.Online;
import com.tenders.entity.TransactionDetails;
import com.tenders.entity.TransactionModeDetails;
import com.tenders.service.TransactionModeService;

@Service
public class TransactionModeServiceImpl implements TransactionModeService {

	@Autowired
	private TransactionDetailsRepository transactionDetailsRepo;

	@Override
	public ApiResponse saveTransactionDetails(TransactionDetailsDto txnDetailsDto) {

		TransactionDetails transDetails = new TransactionDetails();
		transDetails.setTitle(txnDetailsDto.getTitle());
		transDetails.setDescription(txnDetailsDto.getDescription());
		transDetails.setTransactionDate(txnDetailsDto.getTransactionDate());
		transDetails.setTransactionType(txnDetailsDto.getTransactionType());
		transDetails.setTransactionFor(txnDetailsDto.getTransactionFor());
		transDetails.setRecievedFrom(txnDetailsDto.getRecievedFrom());
		transDetails.setPaidTo(txnDetailsDto.getPaidTo());

		if (txnDetailsDto.getModeOfTransaction() != null) {
			ModeOfTransactionDto modeOfTransactionDto = txnDetailsDto.getModeOfTransaction();
			ModeOfTransaction modeOfTransaction = new ModeOfTransaction();
			modeOfTransaction.setModeOfTransaction(modeOfTransactionDto.getModeOfTransaction());

			List<Dd> ddList = new ArrayList<>();
			if (modeOfTransactionDto.getDdDto() != null && !modeOfTransactionDto.getDdDto().isEmpty()) {
				for (DdDto ddDto : modeOfTransactionDto.getDdDto()) {
					Dd dd = new Dd();
					dd.setBankName(ddDto.getBankName());
					dd.setClearDate(ddDto.getClearDate());
					dd.setIssueDate(ddDto.getIssueDate());
					ddList.add(dd);
				}
			}
			modeOfTransaction.setDdDetails(ddList);

			List<Online> onlineList = new ArrayList<>();
			if (modeOfTransactionDto.getOnlineDto() != null && !modeOfTransactionDto.getOnlineDto().isEmpty()) {
				for (OnlineDto onlineDto : modeOfTransactionDto.getOnlineDto()) {
					Online online = new Online();
					online.setBankName(onlineDto.getBankName());
					online.setTransactionNo(onlineDto.getTransactionNo());
					onlineList.add(online);
				}
			}
			modeOfTransaction.setOnlieDetails(onlineList);

			List<CheckEntity> checkEntityList = new ArrayList<CheckEntity>();
			if (modeOfTransactionDto.getCheckDto() != null && !modeOfTransactionDto.getCheckDto().isEmpty()) {
				for (CheckDto checkDto : modeOfTransactionDto.getCheckDto()) {
					CheckEntity checkEntity = new CheckEntity();
					checkEntity.setBankName(checkDto.getBankName());
					checkEntity.setCheckCreateDate(checkDto.getCheckCreateDate());
					checkEntity.setCheckIssueDate(checkDto.getCheckIssueDate());
					checkEntity.setChequeNumber(checkDto.getChequeNumber());
					checkEntityList.add(checkEntity);
				}
			}
			modeOfTransaction.setCheckDetails(checkEntityList);
			transDetails.setModeOfTransaction(modeOfTransaction);
		}
		List<TransactionModeDetails> transactionModeDetailsList = new ArrayList<TransactionModeDetails>();
		if (txnDetailsDto.getTxnModeDeatilsDto() != null && !txnDetailsDto.getTxnModeDeatilsDto().isEmpty()) {
			for (TransactionModeDetailsDto txnModeDetailsDto : txnDetailsDto.getTxnModeDeatilsDto()) {
				TransactionModeDetails txnModeDetails = new TransactionModeDetails();
				txnModeDetails.setBankname(txnModeDetailsDto.getBankname());
				txnModeDetails.setTransactionNumber(txnModeDetailsDto.getTransactionNumber());
				txnModeDetails.setCheckClearDate(txnModeDetailsDto.getCheckClearDate());
				txnModeDetails.setCheckIssueDate(txnModeDetailsDto.getCheckIssueDate());
				txnModeDetails.setDDIssueDate(txnModeDetailsDto.getDDIssueDate());
				txnModeDetails.setDdClearDate(txnModeDetailsDto.getDdClearDate());
				txnModeDetails.setTransactionDetails(transDetails);
				transactionModeDetailsList.add(txnModeDetails);
			}
		}
		transDetails.setTransactionModeDetails(transactionModeDetailsList);

		transactionDetailsRepo.save(transDetails);

		return new ApiResponse(transDetails, HttpStatus.OK);
	}

}
