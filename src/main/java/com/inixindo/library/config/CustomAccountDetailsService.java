package com.inixindo.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inixindo.library.model.Borrower;
import com.inixindo.library.repository.BorrowerRepository;


@Service
public class CustomAccountDetailsService implements UserDetailsService {
	@Autowired
	private BorrowerRepository borrowerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Borrower borrower = borrowerRepository.findByUsername(username);
		if(borrower == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new CustomAccountDetails(borrower);
	}

}
