package com.tenders.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TendersDto {

	private Long id;
	private String tenderNum;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date tenderFloatDate;
	private String tenderFloatingDept;
	// private String userDept;
	private String states;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date biddingDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date prebidDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date bidOpeningDate;
	private Double documentFee;
	private String docFeeStatus;

	private Double emd;
	private String emdStatus;

	private String desc1;
	private Double bg;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date bgExpiryDate;
	private String bgStatus;
	private Integer value;
	private String tenderStatus;
	private String verticals;
	private String assignedTo;
	private String url;
	private String remarks;
	private Double transactionfee;
	private String transactionfeestatus;
	private Double processingfee;
	private String processingfeestatus;
	private Double corpusfee;
	private String corpusfeestatus;
	
	//newly added for getting total document fee
	private double totalDocumentFee;
	private int totalTenders;
	private int awardedTenders;

	
	//added for DashBoard BarGraphs 
	private int countBgstatus;
	private int countDocStatus;
	private int countEmdStatus;
	
	
	@ToString.Exclude
	private NotesDto notesDto;

	@ToString.Exclude
	private List<BomDto> bomDto;
	
	@ToString.Exclude
	private List<UserDeptDto> userDeptDto;
	
	private List<TransactionDetailsDto> transactionDetailsDto;
	
	public double getTotalDocumentFee() {
        return totalDocumentFee;
    }

	    public void setTotalDocumentFee(double totalDocumentFee) {
        this.totalDocumentFee = totalDocumentFee;
    }

//	@JsonCreator
//    public void setTotalDocumentFee(@JsonProperty("totalDocumentFee") double totalDocumentFee) {
//        this.totalDocumentFee = totalDocumentFee;
//    }

}
