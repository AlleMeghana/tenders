package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.Online;

@Repository
public interface OnlineRepository extends JpaRepository<Online, Long>{

}
