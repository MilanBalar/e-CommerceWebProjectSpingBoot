package com.sheryians.major.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomeUserDetail extends TblUser implements UserDetails {

	  //constructor
		public CustomeUserDetail(TblUser user) {
			super(user);

		}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorityList=new ArrayList<GrantedAuthority>();

		super.getTblRoles().forEach(
				  role -> {authorityList.add(new SimpleGrantedAuthority(role.getName()));
			});
      return authorityList;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getEmail();
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
