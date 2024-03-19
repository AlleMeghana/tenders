package com.tenders.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDept {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String deptName;
	private String address;
	private String state;
	private String country;
	

	@ToString.Exclude
	@ManyToOne(targetEntity = Tenders.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userdept_id", referencedColumnName = "id")
	private Tenders tenders;
	
}
