package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.States;

@Repository
public interface StatesRepository extends JpaRepository<States, Long>{

}
