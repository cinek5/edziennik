package com.cinek.edziennik.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.model.User;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.repository.impl.HibernateUserRepository;

@Controller
public class StudentGradesController {

	@Autowired
	private UserRepository userService;

	@RequestMapping("/myGrades")
	public String showGrades(Model model) {
		// TODO
		return "yourgrades";

	}
	

}
