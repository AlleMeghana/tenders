package com.tenders.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tenders.entity.Tenders;

import jakarta.transaction.Transactional;

@Repository
public interface TendersRepository extends JpaRepository<Tenders, Long> {

	Optional<Tenders> findByTenderNum(String tendernum);
	
	List<Tenders> findByTenderStatusNotIn(List<String> excludedValues);

	List<Tenders> findByVerticals(String verticals);

	List<Tenders> findByStates(String states);

	List<Tenders> findByStatesAndVerticals(String states, String verticals);

	@Modifying
	@Transactional
	@Query(value = "SELECT bg_status FROM tenders",nativeQuery = true)
	List<Tenders> findAll1();

	@Modifying
	@Transactional
	@Query(value = "SELECT emd_status FROM tenders",nativeQuery = true)
	List<Tenders> findAll11();

//	@Modifying
//	@Transactional
//	@Query(value = "SELECT emd_status FROM tenders",nativeQuery = true)
//	List<Tenders> findByDocStatus();
	
	
	
}
