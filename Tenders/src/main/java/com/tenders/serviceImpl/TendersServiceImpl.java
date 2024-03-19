package com.tenders.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenders.Repository.AssignedToRepository;
import com.tenders.Repository.StatesRepo;
import com.tenders.Repository.TendersRepository;
import com.tenders.Repository.UserDeptRepository;
import com.tenders.Repository.UsersRepository;
import com.tenders.Repository.VerticalsRepository;
import com.tenders.Repository.tenderStatusRepository;
import com.tenders.dto.ApiResponse;
import com.tenders.dto.BomDto;
import com.tenders.dto.CheckDto;
import com.tenders.dto.DdDto;
import com.tenders.dto.ModeOfTransactionDto;
import com.tenders.dto.OnlineDto;
import com.tenders.dto.TendersDto;
import com.tenders.dto.TransactionDetailsDto;
import com.tenders.dto.TransactionModeDetailsDto;
import com.tenders.entity.AssignedTo;
import com.tenders.entity.Bom;
import com.tenders.entity.CheckEntity;
import com.tenders.entity.Dd;
import com.tenders.entity.ModeOfTransaction;
import com.tenders.entity.Online;
import com.tenders.entity.States;
import com.tenders.entity.TenderStatus;
import com.tenders.entity.Tenders;
import com.tenders.entity.TransactionDetails;
import com.tenders.entity.TransactionModeDetails;
import com.tenders.entity.UserDept;
import com.tenders.entity.Verticals;
import com.tenders.service.TendersService;

@Service
public class TendersServiceImpl implements TendersService {

	@Autowired
	TendersRepository tendersRepo;

	@Autowired
	UsersRepository usersRepository;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Autowired
	private StatesRepo statesRepo;

	@Autowired
	private VerticalsRepository verticalsRepo;

	@Autowired
	private tenderStatusRepository tendersStatusRepo;

	@Autowired
	private AssignedToRepository assignedToRepo;

	@Autowired
	private UserDeptRepository userDeptRepo;

	@Override
	public ApiResponse saveTenders(TendersDto tendersDto) {

//		if (tendersDto != null) {
		Tenders tenders = new Tenders();

//			tenders.setId(tendersDto.getId());

		tenders.setTenderNum(tendersDto.getTenderNum());
		tenders.setTenderFloatDate(tendersDto.getTenderFloatDate());
		tenders.setTenderFloatingDept(tendersDto.getTenderFloatingDept());
		// tenders.setUserDept(tendersDto.getUserDept());

		tenders.setStates(tendersDto.getStates());
		tenders.setBiddingDate(tendersDto.getBiddingDate());
		tenders.setPrebidDate(tendersDto.getPrebidDate());
		tenders.setBidOpeningDate(tendersDto.getBidOpeningDate());
		tenders.setDocumentFee(tendersDto.getDocumentFee());
		tenders.setDocFeeStatus(tendersDto.getDocFeeStatus());
		tenders.setEmd(tendersDto.getEmd());
		tenders.setEmdStatus(tendersDto.getEmdStatus());
		tenders.setDesc1(tendersDto.getDesc1());
		tenders.setBg(tendersDto.getBg());
		tenders.setBgExpiryDate(tendersDto.getBgExpiryDate());
		tenders.setBgStatus(tendersDto.getBgStatus());
		tenders.setValue(tendersDto.getValue());
		tenders.setTenderStatus(tendersDto.getTenderStatus());
		tenders.setVerticals(tendersDto.getVerticals());
		tenders.setAssignedTo(tendersDto.getAssignedTo());
		tenders.setUrl(tendersDto.getUrl());
		tenders.setRemarks(tendersDto.getRemarks());
		tenders.setTransactionfee(tendersDto.getTransactionfee());
		tenders.setTransactionfeestatus(tendersDto.getTransactionfeestatus());
		tenders.setProcessingfee(tendersDto.getProcessingfee());
		tenders.setProcessingfeestatus(tendersDto.getProcessingfeestatus());
		tenders.setCorpusfee(tendersDto.getCorpusfee());
		tenders.setCorpusfeestatus(tendersDto.getCorpusfeestatus());

//		Optional<UserDept> userDeptOpt = userDeptRepo.findById(tendersDto.getUserDeptDto());
		List<UserDept> userD = new ArrayList<UserDept>();
		if (tendersDto.getUserDeptDto() != null && !tendersDto.getUserDeptDto().isEmpty()) {
			Optional<UserDept> userDeptOpt = userDeptRepo.findById(tendersDto.getUserDeptDto().get(0).getId());
			UserDept userDept = userDeptOpt.get();
			if (userDeptOpt.isPresent()) {
				userD.add(userDept);

			}
		}
		tenders.setUserdept(userD);
		List<Bom> bomList = new ArrayList<Bom>();
		for (BomDto bomDto : tendersDto.getBomDto()) {
			Bom bom = new Bom();
			bom.setId(bomDto.getId());
			bom.setItem(bomDto.getItem());
			bom.setQuantity(bomDto.getQuantity());
			bom.setDescription(bomDto.getDescription());
			bom.setTenders(tenders);
			bomList.add(bom);
		}

//		UserDeptDto userdeptdto = tendersDto.getUserDeptDto();
//		UserDept userdept = new UserDept();
////		userdept.setId(userdeptdto.getId());
//		userdept.setDeptName(userdeptdto.getDeptName());
//		userdept.setAddress(userdeptdto.getAddress());
//		userdept.setCountry(userdeptdto.getCountry());
//		userdept.setState(userdeptdto.getState());
//		
//		tenders.setUserdept(userdept);
		tenders.setBom(bomList);

		tendersRepo.save(tenders);
		return new ApiResponse("Inserted successfully " + tenders, HttpStatus.OK);
//		} else {
//
//			return new ApiResponse("No Data found to insert", HttpStatus.BAD_REQUEST);
//		}

	}

	@Override
	public ApiResponse getTenders() {

		List<Tenders> tendersList = tendersRepo.findAll();

		List<Tenders> tenders11 = tendersRepo.findAll1();
		int countBgStatus = tenders11.size();

		List<Tenders> tenders12 = tendersRepo.findAll11();
		int countEmdStatus = tenders12.size();

//		List<Tenders> tenders13=tendersRepo.findByDocStatus();

		if (!tendersList.isEmpty()) {
			List<TendersDto> tendersDtoList = new ArrayList<>();
			for (Tenders tenders1 : tendersList) {
				TendersDto tendersDto = new TendersDto();
				tendersDto.setId(tenders1.getId());
				tendersDto.setTenderNum(tenders1.getTenderNum());
				tendersDto.setTenderFloatDate(tenders1.getTenderFloatDate());
				tendersDto.setTenderFloatingDept(tenders1.getTenderFloatingDept());
				// tendersDto.setUserDept(tenders1.getUserDept());
				tendersDto.setStates(tenders1.getStates());
				tendersDto.setBiddingDate(tenders1.getBiddingDate());
				tendersDto.setPrebidDate(tenders1.getPrebidDate());
				tendersDto.setBidOpeningDate(tenders1.getBidOpeningDate());
				tendersDto.setDocumentFee(tenders1.getDocumentFee());
				tendersDto.setDocFeeStatus(tenders1.getDocFeeStatus());
				tendersDto.setEmd(tenders1.getEmd());
				tendersDto.setEmdStatus(tenders1.getEmdStatus());
				tendersDto.setDesc1(tenders1.getDesc1());
				tendersDto.setBg(tenders1.getBg());
				tendersDto.setBgExpiryDate(tenders1.getBgExpiryDate());
				tendersDto.setBgStatus(tenders1.getBgStatus());
//				tendersDto.setBom(tenders1.getBom());
				tendersDto.setValue(tenders1.getValue());
				tendersDto.setTenderStatus(tenders1.getTenderStatus());
				tendersDto.setVerticals(tenders1.getVerticals());
				tendersDto.setAssignedTo(tenders1.getAssignedTo());
				tendersDto.setUrl(tenders1.getUrl());
				tendersDto.setRemarks(tenders1.getRemarks());
				tendersDto.setTransactionfee(tenders1.getTransactionfee());
				tendersDto.setTransactionfeestatus(tenders1.getTransactionfeestatus());
				tendersDto.setProcessingfee(tenders1.getProcessingfee());
				tendersDto.setProcessingfeestatus(tenders1.getProcessingfeestatus());
				tendersDto.setCorpusfee(tenders1.getCorpusfee());
				tendersDto.setCorpusfeestatus(tenders1.getCorpusfeestatus());
				tendersDto.setCountBgstatus(countBgStatus);
				tendersDto.setCountEmdStatus(countEmdStatus);

				List<BomDto> bomDtoLsit = new ArrayList<BomDto>();
				for (Bom bom : tenders1.getBom()) {
					BomDto bomDto1 = new BomDto();
					bomDto1.setId(bom.getId());
					bomDto1.setDescription(bom.getDescription());
					bomDto1.setItem(bom.getItem());
					bomDto1.setQuantity(bom.getQuantity());
					bomDtoLsit.add(bomDto1);
				}
				tendersDto.setBomDto(bomDtoLsit);

				tendersDtoList.add(tendersDto);
			}

			return new ApiResponse(tendersDtoList, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ApiResponse updateTenders(Long id, TendersDto tendersDto) {

		Optional<Tenders> tendersOptional = tendersRepo.findById(id);
		Tenders tenders = tendersOptional.get();
		if (tendersOptional.isPresent()) {

			tenders.setTenderNum(tendersDto.getTenderNum());
			tenders.setTenderFloatDate(tendersDto.getTenderFloatDate());
			tenders.setTenderFloatingDept(tendersDto.getTenderFloatingDept());
			// tenders.setUserDept(tendersDto.getUserDept());
			tenders.setStates(tendersDto.getStates());
			tenders.setBiddingDate(tendersDto.getBiddingDate());
			tenders.setPrebidDate(tendersDto.getPrebidDate());
			tenders.setBidOpeningDate(tendersDto.getBidOpeningDate());
			tenders.setDocumentFee(tendersDto.getDocumentFee());
			tenders.setDocFeeStatus(tendersDto.getDocFeeStatus());
			tenders.setEmd(tendersDto.getEmd());
			tenders.setEmdStatus(tendersDto.getEmdStatus());
			tenders.setDesc1(tendersDto.getDesc1());
			tenders.setBg(tendersDto.getBg());
			tenders.setBgExpiryDate(tendersDto.getBgExpiryDate());
			tenders.setBgStatus(tendersDto.getBgStatus());
			tenders.setValue(tendersDto.getValue());
			tenders.setTenderStatus(tendersDto.getTenderStatus());
			tenders.setVerticals(tendersDto.getVerticals());
			tenders.setAssignedTo(tendersDto.getAssignedTo());
			tenders.setUrl(tendersDto.getUrl());
			tenders.setRemarks(tendersDto.getRemarks());

			List<Bom> bomList = new ArrayList<Bom>();
			for (BomDto bomDto : tendersDto.getBomDto()) {
				Bom bom = new Bom();
				bom.setId(bomDto.getId());
				bom.setItem(bomDto.getItem());
				bom.setQuantity(bomDto.getQuantity());
				bom.setDescription(bomDto.getDescription());
				bom.setTenders(tenders);
				bomList.add(bom);
			}

//			Optional<UserDept> userDeptOpt = userDeptRepo.findById(tendersDto.getUserDeptDto().getId());
			Optional<UserDept> userDeptOpt = userDeptRepo.findById(tendersDto.getUserDeptDto().get(0).getId());

			UserDept userDept = userDeptOpt.get();
			if (userDeptOpt.isPresent()) {
				List<UserDept> userD = new ArrayList<UserDept>();
				userD.add(userDept);
				tenders.setUserdept(userD);
			}

			List<TransactionDetails> transactionDetailsList = new ArrayList<>();
			for (TransactionDetailsDto transactionsDetailsDto : tendersDto.getTransactionDetailsDto()) {
				TransactionDetails transactionDetails = new TransactionDetails();
				transactionDetails.setTitle(transactionsDetailsDto.getTitle());
				transactionDetails.setDescription(transactionsDetailsDto.getDescription());
				transactionDetails.setTransactionDate(transactionsDetailsDto.getTransactionDate());
				transactionDetails.setTransactionType(transactionsDetailsDto.getTransactionType());
				transactionDetails.setTransactionFor(transactionsDetailsDto.getTransactionFor());
				transactionDetails.setRecievedFrom(transactionsDetailsDto.getRecievedFrom());
				transactionDetails.setPaidTo(transactionsDetailsDto.getPaidTo());

				ModeOfTransactionDto modeOfTransactionDto = transactionsDetailsDto.getModeOfTransaction();
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
				transactionDetails.setModeOfTransaction(modeOfTransaction);

				List<TransactionModeDetails> transactionModeDetailsList = new ArrayList<TransactionModeDetails>();
				for (TransactionModeDetailsDto txnModeDetailsDto : transactionsDetailsDto.getTxnModeDeatilsDto()) {
					TransactionModeDetails txnModeDetails = new TransactionModeDetails();
					txnModeDetails.setBankname(txnModeDetailsDto.getBankname());
					txnModeDetails.setTransactionNumber(txnModeDetailsDto.getTransactionNumber());
					txnModeDetails.setCheckClearDate(txnModeDetailsDto.getCheckClearDate());
					txnModeDetails.setCheckIssueDate(txnModeDetailsDto.getCheckIssueDate());
					txnModeDetails.setDDIssueDate(txnModeDetailsDto.getDDIssueDate());
					txnModeDetails.setDdClearDate(txnModeDetailsDto.getDdClearDate());
					txnModeDetails.setTransactionDetails(transactionDetails);
					transactionModeDetailsList.add(txnModeDetails);
				}
				transactionDetails.setTransactionModeDetails(transactionModeDetailsList);
				transactionDetailsList.add(transactionDetails);
			}

			tenders.setBom(bomList);
			tenders.setTransactionDetails(transactionDetailsList);
			tendersRepo.save(tenders);

			return new ApiResponse("Data updated successfully ", HttpStatus.OK);
		} else {
			return new ApiResponse("No data found to update ", HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ApiResponse getBOMDescTenNum(Long id) {

		Optional<Tenders> tendersList = tendersRepo.findById(id);
		Tenders tenders = tendersList.get();
		List<TendersDto> tendersDtoList = new ArrayList<TendersDto>();
		TendersDto tendersDto = new TendersDto();
//		tendersDto.setBom(tenders.getBom());
		List<BomDto> bomDtoList = new ArrayList<BomDto>();
		for (Bom bom : tenders.getBom()) {
			BomDto bomDto = new BomDto();
			bomDto.setId(bom.getId());
			bomDto.setDescription(bom.getDescription());
			bomDto.setItem(bom.getItem());
			bomDto.setQuantity(bom.getQuantity());
			bomDtoList.add(bomDto);
		}
		tendersDto.setBomDto(bomDtoList);
		tendersDto.setTenderNum(tenders.getTenderNum());
		tendersDto.setDesc1(tenders.getDesc1());
		tendersDtoList.add(tendersDto);

		return new ApiResponse(tendersDtoList, HttpStatus.OK);
	}

	@Override
	public ApiResponse getStates() {
		List<States> statesList = statesRepo.findAll();
		return new ApiResponse(statesList, HttpStatus.OK);
	}

	@Override
	public ApiResponse getVerticals() {
		List<Verticals> verticalsList = verticalsRepo.findAll();
		return new ApiResponse(verticalsList, HttpStatus.OK);
	}

	@Override
	public ApiResponse getAssignedTo() {
		List<AssignedTo> assignedTo = assignedToRepo.findAll();
		return new ApiResponse(assignedTo, HttpStatus.OK);
	}

	@Override
	public ApiResponse getTenderStatus() {
		List<TenderStatus> tenderStatusList = tendersStatusRepo.findAll();
		return new ApiResponse(tenderStatusList, HttpStatus.OK);
	}

	public ApiResponse fetchDataExcludingStaticValues() {
		List<String> excludedValues = Arrays.asList("tender awarded", "TQ disqulified", ""); // Add your static
																								// values
		List<Tenders> tendersList = tendersRepo.findByTenderStatusNotIn(excludedValues);
		List<TendersDto> tendersDtoList = new ArrayList<TendersDto>();
		if (!tendersList.isEmpty()) {
			for (Tenders tenders1 : tendersList) {
				TendersDto tendersDto = new TendersDto();
				tendersDto.setId(tenders1.getId());
				tendersDto.setTenderNum(tenders1.getTenderNum());
				tendersDto.setTenderFloatDate(tenders1.getTenderFloatDate());
				tendersDto.setTenderFloatingDept(tenders1.getTenderFloatingDept());
				// tendersDto.setUserDept(tenders1.getUserDept());
				tendersDto.setStates(tenders1.getStates());
				tendersDto.setBiddingDate(tenders1.getBiddingDate());
				tendersDto.setPrebidDate(tenders1.getPrebidDate());
				tendersDto.setBidOpeningDate(tenders1.getBidOpeningDate());
				tendersDto.setDocumentFee(tenders1.getDocumentFee());
				tendersDto.setDocFeeStatus(tenders1.getDocFeeStatus());
				tendersDto.setEmd(tenders1.getEmd());
				tendersDto.setEmdStatus(tenders1.getEmdStatus());
				tendersDto.setDesc1(tenders1.getDesc1());
				tendersDto.setBg(tenders1.getBg());
				tendersDto.setBgExpiryDate(tenders1.getBgExpiryDate());
				tendersDto.setBgStatus(tenders1.getBgStatus());
//				tendersDto.setBom(tenders1.getBom());
				tendersDto.setValue(tenders1.getValue());
				tendersDto.setTenderStatus(tenders1.getTenderStatus());
				tendersDto.setVerticals(tenders1.getVerticals());
				tendersDto.setAssignedTo(tenders1.getAssignedTo());
				tendersDto.setUrl(tenders1.getUrl());
				tendersDto.setRemarks(tenders1.getRemarks());
				tendersDto.setTransactionfee(tenders1.getTransactionfee());
				tendersDto.setTransactionfeestatus(tenders1.getTransactionfeestatus());
				tendersDto.setProcessingfee(tenders1.getProcessingfee());
				tendersDto.setProcessingfeestatus(tenders1.getProcessingfeestatus());
				tendersDto.setCorpusfee(tenders1.getCorpusfee());
				tendersDto.setCorpusfeestatus(tenders1.getCorpusfeestatus());

				List<BomDto> bomDtoLsit = new ArrayList<BomDto>();
				for (Bom bom : tenders1.getBom()) {
					BomDto bomDto1 = new BomDto();
					bomDto1.setId(bom.getId());
					bomDto1.setDescription(bom.getDescription());
					bomDto1.setItem(bom.getItem());
					bomDto1.setQuantity(bom.getQuantity());
					bomDtoLsit.add(bomDto1);
				}
				tendersDto.setBomDto(bomDtoLsit);
				tendersDtoList.add(tendersDto);
			}

			return new ApiResponse(tendersList, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found", HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ApiResponse fetchVerticals(String verticals) {

		List<Tenders> tendersOpt = tendersRepo.findByVerticals(verticals);
		int totalRetrievedTenders = tendersOpt.size();

		List<Tenders> awardedTenders = tendersOpt.stream()
				.filter(tender -> "Awarded tenders".equals(tender.getTenderStatus())).collect(Collectors.toList());
		int awardedTenders1 = awardedTenders.size();

		TendersDto tendersDto = new TendersDto();
		double totalDocumentFee = tendersOpt.stream().mapToDouble(Tenders::getDocumentFee).sum();
		double emdFee = tendersOpt.stream().mapToDouble(Tenders::getEmd).sum();
		double proccessingFee;
		double curpusFee = tendersOpt.stream().mapToDouble(Tenders::getCorpusfee).sum();
		double bgAmount = tendersOpt.stream().mapToDouble(Tenders::getBg).sum();
		double transavtionFee = tendersOpt.stream().mapToDouble(Tenders::getBg).sum();
		proccessingFee = tendersOpt.stream().mapToDouble(Tenders::getProcessingfee).sum();

		tendersDto.setTotalDocumentFee(totalDocumentFee);
		tendersDto.setEmd(emdFee);
		tendersDto.setCorpusfee(curpusFee);
		tendersDto.setProcessingfee(proccessingFee);
		tendersDto.setBg(bgAmount);
		tendersDto.setTransactionfee(transavtionFee);
		tendersDto.setTotalTenders(totalRetrievedTenders);
		tendersDto.setAwardedTenders(awardedTenders1);

		return new ApiResponse(tendersDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse fetchByStates(String states) {

		List<Tenders> tendersOpt = tendersRepo.findByStates(states);

//		TendersDto tendersDto = new TendersDto();
//		double totalDocumentFee = tendersOpt.stream().mapToDouble(Tenders::getDocumentFee).sum();
//		tendersDto.setTotalDocumentFee(totalDocumentFee);
//		ObjectMapper objectMapper = new ObjectMapper();
//		String totalDocumentFeeJson;
//		try {
//			totalDocumentFeeJson = objectMapper.writeValueAsString(totalDocumentFee);
//			Tenders tenders = objectMapper.readValue(totalDocumentFeeJson, Tenders.class);
//			Double actualTotalDocumentFee = tenders.getDocumentFee();
//			tendersDto.setTotalDocumentFee(actualTotalDocumentFee);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}

		TendersDto tendersDto = new TendersDto();
		double totalDocumentFee = tendersOpt.stream().mapToDouble(Tenders::getDocumentFee).sum();
		double emdFee = tendersOpt.stream().mapToDouble(Tenders::getEmd).sum();
		double proccessingFee;
		double curpusFee = tendersOpt.stream().mapToDouble(Tenders::getCorpusfee).sum();
		double bgAmount = tendersOpt.stream().mapToDouble(Tenders::getBg).sum();
		double transavtionFee = tendersOpt.stream().mapToDouble(Tenders::getBg).sum();
		proccessingFee = tendersOpt.stream().mapToDouble(Tenders::getProcessingfee).sum();

		tendersDto.setTotalDocumentFee(totalDocumentFee);
		tendersDto.setEmd(emdFee);
		tendersDto.setCorpusfee(curpusFee);
		tendersDto.setProcessingfee(proccessingFee);
		tendersDto.setBg(bgAmount);
		tendersDto.setTransactionfee(transavtionFee);

		return new ApiResponse(tendersDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse fetchByVerticalsAndStates(String states, String verticals) {

		List<Tenders> tendersOpt = tendersRepo.findByStatesAndVerticals(states, verticals);
		int totalRetrievedTenders = tendersOpt.size();

		List<Tenders> awardedTenders = tendersOpt.stream()
				.filter(tender -> "Awarded tenders".equals(tender.getTenderStatus())).collect(Collectors.toList());
		int awardedTenders1 = awardedTenders.size();

		TendersDto tendersDto = new TendersDto();
		double totalDocumentFee = tendersOpt.stream().mapToDouble(Tenders::getDocumentFee).sum();
		double emdFee = tendersOpt.stream().mapToDouble(Tenders::getEmd).sum();
		double proccessingFee;
		double curpusFee = tendersOpt.stream().mapToDouble(Tenders::getCorpusfee).sum();
		double bgAmount = tendersOpt.stream().mapToDouble(Tenders::getBg).sum();
		double transavtionFee = tendersOpt.stream().mapToDouble(Tenders::getBg).sum();
		proccessingFee = tendersOpt.stream().mapToDouble(Tenders::getProcessingfee).sum();

		tendersDto.setTotalDocumentFee(totalDocumentFee);
		tendersDto.setEmd(emdFee);
		tendersDto.setCorpusfee(curpusFee);
		tendersDto.setProcessingfee(proccessingFee);
		tendersDto.setBg(bgAmount);
		tendersDto.setTransactionfee(transavtionFee);
		tendersDto.setTotalTenders(totalRetrievedTenders);
		tendersDto.setAwardedTenders(awardedTenders1);

		return new ApiResponse(tendersDto, HttpStatus.OK);

	}

	@Override
	public ApiResponse getTendersByVerticals(String verticals) {

		List<Tenders> tendersList = tendersRepo.findByVerticals(verticals);
		if (!tendersList.isEmpty()) {
			List<TendersDto> tendersDtoList = new ArrayList<>();
			for (Tenders tenders1 : tendersList) {
				TendersDto tendersDto = new TendersDto();
				tendersDto.setId(tenders1.getId());
				tendersDto.setTenderNum(tenders1.getTenderNum());
				tendersDto.setTenderFloatDate(tenders1.getTenderFloatDate());
				tendersDto.setTenderFloatingDept(tenders1.getTenderFloatingDept());
				// tendersDto.setUserDept(tenders1.getUserDept());
				tendersDto.setStates(tenders1.getStates());
				tendersDto.setBiddingDate(tenders1.getBiddingDate());
				tendersDto.setPrebidDate(tenders1.getPrebidDate());
				tendersDto.setBidOpeningDate(tenders1.getBidOpeningDate());
				tendersDto.setDocumentFee(tenders1.getDocumentFee());
				tendersDto.setDocFeeStatus(tenders1.getDocFeeStatus());
				tendersDto.setEmd(tenders1.getEmd());
				tendersDto.setEmdStatus(tenders1.getEmdStatus());
				tendersDto.setDesc1(tenders1.getDesc1());
				tendersDto.setBg(tenders1.getBg());
				tendersDto.setBgExpiryDate(tenders1.getBgExpiryDate());
				tendersDto.setBgStatus(tenders1.getBgStatus());
//				tendersDto.setBom(tenders1.getBom());
				tendersDto.setValue(tenders1.getValue());
				tendersDto.setTenderStatus(tenders1.getTenderStatus());
				tendersDto.setVerticals(tenders1.getVerticals());
				tendersDto.setAssignedTo(tenders1.getAssignedTo());
				tendersDto.setUrl(tenders1.getUrl());
				tendersDto.setRemarks(tenders1.getRemarks());
				tendersDto.setTransactionfee(tenders1.getTransactionfee());
				tendersDto.setTransactionfeestatus(tenders1.getTransactionfeestatus());
				tendersDto.setProcessingfee(tenders1.getProcessingfee());
				tendersDto.setProcessingfeestatus(tenders1.getProcessingfeestatus());
				tendersDto.setCorpusfee(tenders1.getCorpusfee());
				tendersDto.setCorpusfeestatus(tenders1.getCorpusfeestatus());

				List<BomDto> bomDtoLsit = new ArrayList<BomDto>();
				for (Bom bom : tenders1.getBom()) {
					BomDto bomDto1 = new BomDto();
					bomDto1.setId(bom.getId());
					bomDto1.setDescription(bom.getDescription());
					bomDto1.setItem(bom.getItem());
					bomDto1.setQuantity(bom.getQuantity());
					bomDtoLsit.add(bomDto1);
				}
				tendersDto.setBomDto(bomDtoLsit);

				tendersDtoList.add(tendersDto);
			}

			return new ApiResponse(tendersDtoList, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ApiResponse getTendersByStates(String states) {

		List<Tenders> tendersList = tendersRepo.findByStates(states);
		if (!tendersList.isEmpty()) {
			List<TendersDto> tendersDtoList = new ArrayList<>();
			for (Tenders tenders1 : tendersList) {
				TendersDto tendersDto = new TendersDto();
				tendersDto.setId(tenders1.getId());
				tendersDto.setTenderNum(tenders1.getTenderNum());
				tendersDto.setTenderFloatDate(tenders1.getTenderFloatDate());
				tendersDto.setTenderFloatingDept(tenders1.getTenderFloatingDept());
				// tendersDto.setUserDept(tenders1.getUserDept());
				tendersDto.setStates(tenders1.getStates());
				tendersDto.setBiddingDate(tenders1.getBiddingDate());
				tendersDto.setPrebidDate(tenders1.getPrebidDate());
				tendersDto.setBidOpeningDate(tenders1.getBidOpeningDate());
				tendersDto.setDocumentFee(tenders1.getDocumentFee());
				tendersDto.setDocFeeStatus(tenders1.getDocFeeStatus());
				tendersDto.setEmd(tenders1.getEmd());
				tendersDto.setEmdStatus(tenders1.getEmdStatus());
				tendersDto.setDesc1(tenders1.getDesc1());
				tendersDto.setBg(tenders1.getBg());
				tendersDto.setBgExpiryDate(tenders1.getBgExpiryDate());
				tendersDto.setBgStatus(tenders1.getBgStatus());
//				tendersDto.setBom(tenders1.getBom());
				tendersDto.setValue(tenders1.getValue());
				tendersDto.setTenderStatus(tenders1.getTenderStatus());
				tendersDto.setVerticals(tenders1.getVerticals());
				tendersDto.setAssignedTo(tenders1.getAssignedTo());
				tendersDto.setUrl(tenders1.getUrl());
				tendersDto.setRemarks(tenders1.getRemarks());
				tendersDto.setTransactionfee(tenders1.getTransactionfee());
				tendersDto.setTransactionfeestatus(tenders1.getTransactionfeestatus());
				tendersDto.setProcessingfee(tenders1.getProcessingfee());
				tendersDto.setProcessingfeestatus(tenders1.getProcessingfeestatus());
				tendersDto.setCorpusfee(tenders1.getCorpusfee());
				tendersDto.setCorpusfeestatus(tenders1.getCorpusfeestatus());

				List<BomDto> bomDtoLsit = new ArrayList<BomDto>();
				for (Bom bom : tenders1.getBom()) {
					BomDto bomDto1 = new BomDto();
					bomDto1.setId(bom.getId());
					bomDto1.setDescription(bom.getDescription());
					bomDto1.setItem(bom.getItem());
					bomDto1.setQuantity(bom.getQuantity());
					bomDtoLsit.add(bomDto1);
				}
				tendersDto.setBomDto(bomDtoLsit);

				tendersDtoList.add(tendersDto);
			}

			return new ApiResponse(tendersDtoList, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ApiResponse getTendersByStatesAndVerticals(String states, String verticals) {

		List<Tenders> tendersList = tendersRepo.findByStatesAndVerticals(states, verticals);
		if (!tendersList.isEmpty()) {
			List<TendersDto> tendersDtoList = new ArrayList<>();
			for (Tenders tenders1 : tendersList) {
				TendersDto tendersDto = new TendersDto();
				tendersDto.setId(tenders1.getId());
				tendersDto.setTenderNum(tenders1.getTenderNum());
				tendersDto.setTenderFloatDate(tenders1.getTenderFloatDate());
				tendersDto.setTenderFloatingDept(tenders1.getTenderFloatingDept());
				// tendersDto.setUserDept(tenders1.getUserDept());
				tendersDto.setStates(tenders1.getStates());
				tendersDto.setBiddingDate(tenders1.getBiddingDate());
				tendersDto.setPrebidDate(tenders1.getPrebidDate());
				tendersDto.setBidOpeningDate(tenders1.getBidOpeningDate());
				tendersDto.setDocumentFee(tenders1.getDocumentFee());
				tendersDto.setDocFeeStatus(tenders1.getDocFeeStatus());
				tendersDto.setEmd(tenders1.getEmd());
				tendersDto.setEmdStatus(tenders1.getEmdStatus());
				tendersDto.setDesc1(tenders1.getDesc1());
				tendersDto.setBg(tenders1.getBg());
				tendersDto.setBgExpiryDate(tenders1.getBgExpiryDate());
				tendersDto.setBgStatus(tenders1.getBgStatus());
//				tendersDto.setBom(tenders1.getBom());
				tendersDto.setValue(tenders1.getValue());
				tendersDto.setTenderStatus(tenders1.getTenderStatus());
				tendersDto.setVerticals(tenders1.getVerticals());
				tendersDto.setAssignedTo(tenders1.getAssignedTo());
				tendersDto.setUrl(tenders1.getUrl());
				tendersDto.setRemarks(tenders1.getRemarks());
				tendersDto.setTransactionfee(tenders1.getTransactionfee());
				tendersDto.setTransactionfeestatus(tenders1.getTransactionfeestatus());
				tendersDto.setProcessingfee(tenders1.getProcessingfee());
				tendersDto.setProcessingfeestatus(tenders1.getProcessingfeestatus());
				tendersDto.setCorpusfee(tenders1.getCorpusfee());
				tendersDto.setCorpusfeestatus(tenders1.getCorpusfeestatus());

				List<BomDto> bomDtoLsit = new ArrayList<BomDto>();
				for (Bom bom : tenders1.getBom()) {
					BomDto bomDto1 = new BomDto();
					bomDto1.setId(bom.getId());
					bomDto1.setDescription(bom.getDescription());
					bomDto1.setItem(bom.getItem());
					bomDto1.setQuantity(bom.getQuantity());
					bomDtoLsit.add(bomDto1);
				}
				tendersDto.setBomDto(bomDtoLsit);

				tendersDtoList.add(tendersDto);
			}

			return new ApiResponse(tendersDtoList, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found", HttpStatus.BAD_REQUEST);
		}
	}

}
