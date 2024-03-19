package com.tenders.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate transactionDate;
	private String transactionType;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToOne(targetEntity =ModeOfTransaction .class,cascade = CascadeType.ALL)
	@JoinColumn(name = "modeOfTxn_id",referencedColumnName = "id")
	private ModeOfTransaction modeOfTransaction;
	private String transactionFor;
	private String recievedFrom;
	private String paidTo;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = Tenders.class)
	@JoinColumn(name = "tender_id",referencedColumnName = "id")
	private Tenders tenders;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(targetEntity = TransactionModeDetails.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "transaction_id",referencedColumnName = "id")
	private List<TransactionModeDetails> transactionModeDetails=new ArrayList<>();

}
