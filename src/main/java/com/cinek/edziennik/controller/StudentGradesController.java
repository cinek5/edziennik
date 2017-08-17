package com.cinek.edziennik.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinek.edziennik.exception.NoSuchGradeException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.CourseService;
import com.cinek.edziennik.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentGradesController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;

	@RequestMapping("/myGrades")
	public String showGrades(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Set<Grade> grades = studentService.getStudentGradesByUsername(username);
		model.addAttribute("grades", grades);
		return "yourgrades";

	}

	@RequestMapping("/signIn")
	public String showCoursesAvaible(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Set<Course> courses = studentService.getCoursesAvaibleToSingIn(username);
		model.addAttribute("courses", courses);
		return "studentCoursesAvaible";
	}

	@RequestMapping("/signIn/{courseId}")
	public String signToCourse(Model model, @PathVariable Long courseId) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Long studentId = ((Student) (userRepository.findByUsername(username))).getId();
		courseService.addStudentToCourse(studentId, courseId);

		return "redirect:/student/signIn";

	}

	@RequestMapping("/acceptGrade/{gradeId}")
	public String acceptGrade(@PathVariable Long gradeId) {
		
		try {
			studentService.acceptGrade(gradeId);
		} catch (NoSuchGradeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/student/myGrades";

	}

}
