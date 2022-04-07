package com.inixindo.library.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;

@Data
@Entity(name = "borrower")
public class Borrower {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)	
	public int cardNo;
	
	@Column(nullable = false)
	public String Name;
	
	@Column(nullable = false)
	public String Address;
	
	@Column(nullable = false)
	public String Phone;
	
	@Column(nullable = false)
	public String username;
	
	@Column(nullable = false)
	public String role;
	
	@Column(nullable = false)
	public String password;
	
//	@OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
//	private List<Loans> loans;
//	
	
	public void setPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password = passwordEncoder.encode(password).toString();
	}
}
