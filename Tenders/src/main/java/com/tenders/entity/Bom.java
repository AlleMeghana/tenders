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
@AllArgsConstructor
@NoArgsConstructor

public class Bom {

//	@Override
//	public String toString() {
//		return "Bom [id=" + id + ", item=" + item + ", description=" + description + ", quantity=" + quantity
//				+ ", tenders=" + tenders + "]";
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String item;
	private String description;
	private Integer quantity;

	@ToString.Exclude
	@ManyToOne(targetEntity = Tenders.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "bom_id", referencedColumnName = "id")
	private Tenders tenders;
	
	
}
