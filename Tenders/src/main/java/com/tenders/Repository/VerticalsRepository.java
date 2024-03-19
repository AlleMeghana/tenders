package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.Verticals;

@Repository
public interface VerticalsRepository extends JpaRepository<Verticals, Long>{

}
