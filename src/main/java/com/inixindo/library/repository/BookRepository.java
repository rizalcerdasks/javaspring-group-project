package com.inixindo.library.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.inixindo.library.model.Books;
import com.inixindo.library.model.Loans;

public interface BookRepository extends JpaRepository<Books, Integer> {
	//List<Books> findAll();
	@Modifying
	@Query
		(value = "select * from books where no_of_copies != 0", nativeQuery = true)
	List<Books> getAvailableBook();
	
	@Transactional
	@Modifying
	@Query
	(value ="update books set no_of_copies = no_of_copies - 1 where bookid = ?1",nativeQuery = true)
	void minStock(int id);
	
	@Transactional
	@Modifying
	@Query
	(value ="update books set no_of_copies = no_of_copies + 1 where bookid = ?1",nativeQuery = true)
	void returnStock(int id);
}
