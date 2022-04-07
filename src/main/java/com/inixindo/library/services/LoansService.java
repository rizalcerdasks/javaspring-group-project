package com.inixindo.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inixindo.library.model.Loans;
import com.inixindo.library.repository.LoansRepository;

@Service
public class LoansService {
	@Autowired
	LoansRepository loansRepository;

	public List<Loans> listAll() {
		// TODO Auto-generated method stub
		return loansRepository.findAll();
	}

	public Loans save(Loans loans) {
		// TODO Auto-generated method stub
		return loansRepository.save(loans);

	}

	public Loans get(int id) {
		// TODO Auto-generated method stub
		return loansRepository.findById(id).get();
	}
	
	
	public List<Loans> getLoansByCardNo(int card_no){
		return loansRepository.findLoansByCardNumber(card_no);
	}
	
	public List<Loans> getHistoryLoan(int card_no){
		return loansRepository.findHistoryLoan(card_no);
	}
	
	public List<Loans> getLoans(){
		return loansRepository.findLoans();
	}
	
	public void bookReturned(int loan_id) {
		loansRepository.bookReturned(loan_id);
		return ;
	}
	
	public void delete(int id) {
		// TODO Auto-generated method stub
		loansRepository.deleteById(id);
	}
}
