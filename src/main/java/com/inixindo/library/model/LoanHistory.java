package com.inixindo.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name = "loan_history")
public class LoanHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	public int LoansID;

	@Column(nullable = false)
	public String DateOut;

	@Column(nullable = false)
	public String DueData;

	@OneToOne

	@JoinColumn(name = "bookid")
	public Books books;

	@OneToOne

	@JoinColumn(name = "cardNo")
	public Borrower borrower;

}
