package com.cinek.edziennik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinek.edziennik.exception.NoSelectedOptionException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.service.CourseService;
import com.cinek.edziennik.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	CourseService courseService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		List<Teacher> teachers = userService.getAllTeachers();
		model.addAttribute("teachers", teachers);
		return "addCourseForm";

	}

	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("course") @Valid Course course, BindingResult result,
			HttpServletRequest request) throws NoSelectedOptionException {
		if (result.hasErrors()) {
			// errors when filling form
			return "addCourseForm";
		} else {
			String id = request.getParameter("selectbox");
			if (id == null) {
			   throw new NoSelectedOptionException();
			}
			courseService.insertCourse(course);
			courseService.addTeacherToCourse(Long.parseLong(id), course.getId());

			return "redirect:/";
		}
	}

}
