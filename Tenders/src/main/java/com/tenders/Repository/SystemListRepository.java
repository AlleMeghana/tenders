package com.tenders.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.SystemList;

@Repository
public interface SystemListRepository extends JpaRepository<SystemList, Long>{

	List<SystemList> findByName(String name);

	
}
