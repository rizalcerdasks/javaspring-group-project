package com.inixindo.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "publisher")
public class Publisher {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public int PublisherID;
	@Column(nullable = false)
	public String Name;
	@Column(nullable = false)
	public String Address;
	@Column(nullable = false)
	public String Phone;
}
