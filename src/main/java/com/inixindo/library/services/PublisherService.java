package com.inixindo.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inixindo.library.model.Books;
import com.inixindo.library.model.Publisher;
import com.inixindo.library.repository.PublisherRepository;

@Service
public class PublisherService {

	@Autowired
	PublisherRepository publisherRepository;

	public List<Publisher> listAll() {
		// TODO Auto-generated method stub
		return publisherRepository.findAll();
	}

	public Publisher save(Publisher publisher) {
		// TODO Auto-generated method stub
		return publisherRepository.save(publisher);

	}

	public Publisher get(int id) {
		// TODO Auto-generated method stub
		return publisherRepository.findById(id).get();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		publisherRepository.deleteById(id);
	}
}
