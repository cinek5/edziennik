package com.cinek.edziennik.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinek.edziennik.exception.StudentNoSuchCourseException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.service.CourseService;
import com.cinek.edziennik.service.UserService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;

	@RequestMapping("/setGrade/{studentId}/{courseId}/{grade}")
	public String setGrade(Model model, @PathVariable Long studentId, @PathVariable Long courseId,
			@PathVariable Double grade) {
		try {
			courseService.setGradeFromCourse(studentId, courseId, grade);
		} catch (StudentNoSuchCourseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);

		return "teacherRegisterForm";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("teacher") @Valid Teacher teacher, BindingResult result) {
		if (result.hasErrors()) {
			// errors when filling form
			return "teacherRegisterForm";
		} else {
			userService.insertUser(teacher);
			return "redirect:/";
		}
	}

}
