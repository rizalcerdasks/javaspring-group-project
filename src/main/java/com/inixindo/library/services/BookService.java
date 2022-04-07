package com.inixindo.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inixindo.library.model.Books;
import com.inixindo.library.repository.BookRepository;

@Service

public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Books> listAll() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}
     
	public Books save(Books books) {
		// TODO Auto-generated method stub
		return bookRepository.save(books);
		
	}

	public Books get(int id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id).get();
	}
	
	public List<Books> getBookAvailable() {
		return bookRepository.getAvailableBook();
	}
	
	public void minStock(int id) {
		bookRepository.minStock(id);
		return ;
	}
	
	public void returnStock(int id) {
		bookRepository.returnStock(id);
		return ;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(id);
	}
}
