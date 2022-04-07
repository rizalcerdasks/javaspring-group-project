package com.inixindo.library.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.inixindo.library.model.Loans;

public interface LoansRepository extends JpaRepository<Loans, Integer> {
	//List<Loans> findAll();
	@Modifying
	@Query
		(value = "select * from loans where card_no = ?1 and status = 'borrowed'", nativeQuery = true)
	List<Loans> findLoansByCardNumber(int card_no);
	
	@Modifying
	@Query
		(value = "select * from loans where card_no = ?1 and status = 'returned'", nativeQuery = true)
	List<Loans> findHistoryLoan(int card_no);
	
	@Modifying
	@Query
	(value = "select * from loans where status = 'borrowed'", nativeQuery = true)
	List<Loans> findLoans();
	
	
	@Transactional
	@Modifying
	@Query
	(value ="update loans set status = 'returned' where loansid = ?1",nativeQuery = true)
	void bookReturned(int id);
}
