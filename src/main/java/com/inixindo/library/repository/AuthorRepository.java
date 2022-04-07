package com.inixindo.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inixindo.library.model.Authors;

public interface AuthorRepository extends JpaRepository<Authors, Integer>{
	//List<Authors> findAll();
}
