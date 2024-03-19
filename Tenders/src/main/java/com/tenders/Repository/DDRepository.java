package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.Dd;

@Repository
public interface DDRepository extends JpaRepository<Dd, Long> {

}
