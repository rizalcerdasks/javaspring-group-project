package com.inixindo.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inixindo.library.model.Authors;
import com.inixindo.library.model.Publisher;
import com.inixindo.library.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;
	
	public List<Authors> listAll() {
		// TODO Auto-generated method stub
		return authorRepository.findAll();
	}

	public Authors save(Authors authors) {
		// TODO Auto-generated method stub
		return authorRepository.save(authors);

	}

	public Authors get(int id) {
		// TODO Auto-generated method stub
		return authorRepository.findById(id).get();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		authorRepository.deleteById(id);
	}
}
