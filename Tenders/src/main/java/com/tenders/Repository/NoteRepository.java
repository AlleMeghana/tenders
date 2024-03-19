package com.tenders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.Notes;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {

}
