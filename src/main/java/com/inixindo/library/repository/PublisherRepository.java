package com.inixindo.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inixindo.library.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
	//List<Publisher> findAll();
}
