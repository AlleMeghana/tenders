package com.tenders.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Tenders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String tenderNum;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date tenderFloatDate;
	private String tenderFloatingDept;
	//private String userDept;
	private String states;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date biddingDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date prebidDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
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

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(targetEntity = TransactionDetails.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "tender_id",referencedColumnName = "id")
	private List<TransactionDetails> transactionDetails=new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(targetEntity = Notes.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "notes_id",referencedColumnName = "id")
	private List<Notes> notes=new ArrayList<Notes>();
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(targetEntity = Bom.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "bom_id",referencedColumnName = "id")
	private List<Bom> bom=new ArrayList<Bom>();
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(targetEntity = UserDept.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "userdept_id",referencedColumnName = "id")
	private List<UserDept> userdept=new ArrayList<UserDept>() ;

	public void setBgExpiryDate(Date bgExpiryDate) {
		// TODO Auto-generated method stub
		
	}
		
	
}	 
