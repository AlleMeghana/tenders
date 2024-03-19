package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.TenderStatus;

@Repository
public interface tenderStatusRepository extends JpaRepository<TenderStatus, Long> {

}
