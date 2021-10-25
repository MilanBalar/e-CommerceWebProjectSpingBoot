package com.sheryians.major.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sheryians.major.entity.CustomeUserDetail;
import com.sheryians.major.entity.TblUser;
import com.sheryians.major.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<TblUser> user = userRepository.findTblUserByEmail(username);
		user.orElseThrow(()->new UsernameNotFoundException("User not exist !!"));
		if(user==null)
		 {
			 throw new UsernameNotFoundException("Could't found the user !!");
		 }
		 CustomeUserDetail customUserDetails=new CustomeUserDetail(user.get());
		 return customUserDetails;
	}

}
