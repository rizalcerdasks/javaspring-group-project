package com.inixindo.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inixindo.library.model.Borrower;
import com.inixindo.library.repository.BorrowerRepository;

@Service
public class BorrowerService {
	@Autowired
	private BorrowerRepository borrowerRepository;
	
	public List<Borrower> listAll() {
		// TODO Auto-generated method stub
		return borrowerRepository.findAll();
	}
     
	public Borrower save(Borrower borrower) {
		// TODO Auto-generated method stub
		return borrowerRepository.save(borrower);
		
	}

	public Borrower get(int id) {
		// TODO Auto-generated method stub
		return borrowerRepository.findById(id).get();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		borrowerRepository.deleteById(id);
	}
	
	public Borrower findByUsername(String name){
		return borrowerRepository.findByUsername(name);
	}
	
	
	public List<String> listBorrowedBook(String name) {
		// TODO Auto-generated method stub
		return borrowerRepository.findBorrowerBookByUsername(name);
	}
	
	public List<Borrower> getBorrowerNewBook() {
		// TODO Auto-generated method stub
		return borrowerRepository.findBorrower();
	}
}
