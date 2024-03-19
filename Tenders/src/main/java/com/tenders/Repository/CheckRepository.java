package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.CheckEntity;

@Repository
public interface CheckRepository extends JpaRepository<CheckEntity, Long>{

}
