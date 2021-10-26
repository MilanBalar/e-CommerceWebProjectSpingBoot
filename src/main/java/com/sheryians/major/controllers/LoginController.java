package com.sheryians.major.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sheryians.major.entity.TblRoles;
import com.sheryians.major.entity.TblUser;
import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/register")
	public String registerGET() {
		return "register";
	}
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") TblUser user,HttpServletRequest request) throws ServletException {
		String password=user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setFisrtName(user.getFisrtName());
		user.setLastName(user.getLastName());
		List<TblRoles> tblRoles=new ArrayList<TblRoles>();
		tblRoles.add(roleRepository.findById(2).get());//for user role id is 2
		user.setTblRoles(tblRoles);
		userRepository.save(user);

		//auto login after the register
		request.login(user.getEmail(), password);
        return "redirect:/";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}


}
