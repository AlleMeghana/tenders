package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.UserDept;

@Repository
public interface UserDeptRepository extends JpaRepository<UserDept, Long> {



	
}
