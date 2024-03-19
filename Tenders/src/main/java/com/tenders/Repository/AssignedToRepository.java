package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.AssignedTo;

@Repository
public interface AssignedToRepository extends JpaRepository<AssignedTo, Long> {

}
