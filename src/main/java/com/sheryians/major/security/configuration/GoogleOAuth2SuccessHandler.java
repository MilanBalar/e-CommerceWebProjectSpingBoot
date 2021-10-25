package com.sheryians.major.security.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sheryians.major.entity.TblRoles;
import com.sheryians.major.entity.TblUser;
import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.repository.UserRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();//it's use for redirect the request
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2AuthenticationToken token=(OAuth2AuthenticationToken) authentication;
		String email=token.getPrincipal().getAttributes().get("email").toString();
		if(userRepository.findTblUserByEmail(email).isPresent()) {
			//means user is already present in our db. so not need to save this user
		}else {
			TblUser tblUser=new TblUser();
			tblUser.setFisrtName(token.getPrincipal().getAttributes().get("given_name").toString());
			tblUser.setFisrtName(token.getPrincipal().getAttributes().get("family_name").toString());
			tblUser.setEmail(email);
			List<TblRoles> tblRoles=new ArrayList<TblRoles>();
			tblRoles.add(roleRepository.findById(2).get());//for user role id is 2
			tblUser.setTblRoles(tblRoles);
			userRepository.save(tblUser);
		}
      redirectStrategy.sendRedirect(request, response, "/");
	}

}
