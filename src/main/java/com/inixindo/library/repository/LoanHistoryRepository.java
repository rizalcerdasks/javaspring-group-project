package com.inixindo.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inixindo.library.model.LoanHistory;

public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Integer>{
	//List<Copies> findAll();
}
