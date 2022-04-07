package com.inixindo.library.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name = "books")
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	public int BookID;
	@Column(nullable = false)
	public  String title;
	@ManyToOne
	@JoinColumn(name = "author_id")
	public  Authors authors;

	public int no_of_copies;
	@OneToOne
	@JoinColumn(name ="publisher_id")
	public Publisher publisher;
//	
//	@OneToMany(mappedBy = "books", cascade = CascadeType.ALL)
//	public List<Loans> loans;
}
