package com.inixindo.library.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inixindo.library.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Integer>{

	Borrower findByUsername(String username);
	List<String> borrowedBooks = new ArrayList<>();

@Modifying
@Query
	(value = "SELECT l.loansid, b.title, br.name, l.date_out, l.due_data FROM loans l"
			+ " INNER JOIN borrower br ON (br.card_no = l.card_no)"
			+ " INNER JOIN books b ON (b.bookid = l.bookid) "
			+ "WHERE br.name = ?1", nativeQuery = true)
	List<String> findBorrowerBookByUsername(String name);

@Modifying
@Query
	(value = "select * from borrower where role != 'LIBRARIAN'", nativeQuery = true)
	List<Borrower> findBorrower();


}

/*
 * 
 * SELECT l.loansid, b.title, br.name, l.date_out, l.due_data " +
 * "FROM loans l " + "JOIN borrower br ON (br.card_no = l.card_no) " +
 * "JOIN books b ON (b.bookid = l.bookid) " + "WHERE br.name = ?1
 */