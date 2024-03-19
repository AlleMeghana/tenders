package com.tenders.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

public class TransactionModeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String bankname;
	public String transactionNumber;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIssueDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkClearDate;
	public String checkNumber;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dDIssueDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate ddClearDate;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = TransactionDetails.class)
	@JoinColumn(name = "transaction_id",referencedColumnName = "id")
	private TransactionDetails transactionDetails;

}
