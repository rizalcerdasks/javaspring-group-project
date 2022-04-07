package com.inixindo.library.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.inixindo.library.model.Borrower;


public class CustomAccountDetails implements UserDetails{

	private Borrower borrower;
	
	public CustomAccountDetails(Borrower borrower) {
		super();
		this.borrower = borrower;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority(borrower.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return borrower.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return borrower.getUsername();
	}
	
	public String getRole() {
		return borrower.getRole();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
