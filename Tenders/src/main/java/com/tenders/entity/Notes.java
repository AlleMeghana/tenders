package com.tenders.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String loggedBy;
	private LocalDate loggedTime;
	private String note;
	
	@ManyToOne(targetEntity = Tenders.class)
	@JoinColumn(name = "notes_id",referencedColumnName = "id")
	private Tenders tenders;
	
	@PrePersist
    protected void onCreate() {
		loggedTime = LocalDate.now();
    }
	
	@ToString.Exclude
    @ManyToOne(targetEntity = UsersEntity.class)
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private UsersEntity user;

}
	
	

