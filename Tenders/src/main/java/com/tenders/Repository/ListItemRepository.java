package com.tenders.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenders.entity.ListItems;

@Repository
public interface ListItemRepository extends JpaRepository<ListItems, Long> {

//	List<SystemList> findByName(String name);

	List<ListItems> findByListName(String listName);

	List<ListItems> findBySystemListId(Long systemId);

}
