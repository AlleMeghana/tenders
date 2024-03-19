package com.tenders.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenders.Repository.BomRepository;
import com.tenders.Repository.TendersRepository;
import com.tenders.dto.ApiResponse;
import com.tenders.dto.BomDto;
import com.tenders.entity.Bom;
import com.tenders.entity.Tenders;
import com.tenders.service.BomService;
@Service
public class BomServiceImpl implements BomService {
	
	@Autowired
	TendersRepository tendersRepo;
	
	@Autowired
	BomRepository bomRepo;
	@Override
	public ApiResponse updateBOM(Long id,BomDto bomDto) {
		Optional<Tenders> tendersList=tendersRepo.findById(id);
		Tenders tenders=tendersList.get();
		List<Bom> bomList=new ArrayList<>(); 
		for(Bom t1:tendersList.get().getBom()) {
//			Bom BomDto=new Bom();
			t1.setDescription(bomDto.getDescription());
			t1.setItem(bomDto.getItem());
			t1.setQuantity(bomDto.getQuantity());
			bomList.add(t1);
		}
		tenders.setBom(bomList);
		tendersRepo.save(tenders);
		return new ApiResponse("Inserted successfully " + bomList, HttpStatus.OK);
		
	}
	
	@Override
	public ApiResponse getBom() {

		List<Bom> bomList = bomRepo.findAll();
		if (!bomList.isEmpty()) {
			List<BomDto> bomDtoList = new ArrayList<>();
			for (Bom bom : bomList) {
				BomDto bomDto = new BomDto();
			    bomDto.setId(bom.getId());
				bomDto.setItem(bom.getItem());
				bomDto.setDescription(bom.getDescription());
				bomDto.setQuantity(bom.getQuantity());
				bomDtoList.add(bomDto);
			}
			return new ApiResponse(bomDtoList, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found", HttpStatus.BAD_REQUEST);
		}}
	
	
//	public void deleteBomById(Long bomId) {
////        Optional<Bom> bomOptional = bomRepo.findById(bomId);
////        bomOptional.ifPresent(bom -> {
////            Tenders tenders = bom.getTenders();
////            if (tenders != null) {
////                tenders.getBom().removeIf(b -> b.getId().equals(bomId)); // Remove the specific Bom entity
////                bomRepo.deleteById(bomId);
//            }
////        });
//
//    }



}
