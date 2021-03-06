package com.cinek.edziennik.controller;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cinek.edziennik.service.StudentService;
import com.cinek.edziennik.service.impl.ExampleEntitesCreatorService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	ExampleEntitesCreatorService entcreator;
	@Autowired
	StudentService studentService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		return "home";
	}
	
	@RequestMapping(value = "/createEntites", method = RequestMethod.GET)
	public String check(Locale locale, Model model) {
		
		entcreator.createEntites();
	
		
		
		return "redirect:/";
	}
	@RequestMapping(value = "/topStudents", method = RequestMethod.GET)
	public String topStudents(Model model) {
		
		model.addAttribute("studentsMap",studentService.getStudentsMapSortedByGrades());
		return "topStudents";
		
	}
}
