package com.tenders.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

public class ModeOfTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String modeOfTransaction;
	
	@ToString.Exclude
	@OneToMany(targetEntity = Dd.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "mode_id",referencedColumnName = "id")
	private List<Dd> ddDetails=new ArrayList<>();
	
	@ToString.Exclude
	@OneToMany(targetEntity = Online.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "mode_id",referencedColumnName = "id")
	private List<Online> onlieDetails=new ArrayList<>();
	
	@ToString.Exclude
	@OneToMany(targetEntity = CheckEntity.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "mode_id",referencedColumnName = "id")
	private List<CheckEntity> checkDetails=new ArrayList<>();


}
