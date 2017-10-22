package com.cinek.edziennik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinek.edziennik.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/myProfile")
	public String showMyProfile(Model model) {
		User userauth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userauth.getUsername();
        com.cinek.edziennik.model.User  user = userService.findByUsername(username);
        model.addAttribute("myProfile",true);
        model.addAttribute("user",user);
		return "myProfile";
	}
	@RequestMapping("/user/{id}")
	public String showUserProfile(Model model, @PathVariable Long id) {
		com.cinek.edziennik.model.User user = userService.findById(id);
        model.addAttribute("user",user);
		return "myProfile";
	}
	
}
