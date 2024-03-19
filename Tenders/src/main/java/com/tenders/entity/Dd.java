package com.tenders.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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

public class Dd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate issueDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate clearDate;
	private String bankName;
	
	@ManyToOne(targetEntity = ModeOfTransaction.class)
	@JoinColumn(name = "mode_id",referencedColumnName = "id")
	private ModeOfTransaction modeOfTransaction;
	
}
