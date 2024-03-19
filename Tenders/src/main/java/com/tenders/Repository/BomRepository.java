package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenders.entity.Bom;

public interface BomRepository extends JpaRepository<Bom, Long>  {

}
