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
	@RequestMapping("/dbTest") 
	public String dbTest(Model model) {
		Student student = new Student();
		student.setName("Marcin");
		student.setBirthdate(LocalDate.of(1997, 10, 2));
		student.setEmail("cinek5@gmail.com");
		student.setSurname("Krawczyk");
		student.setUsername("cinek5");
		student.setPassword("cineczek");
		
		Teacher teacher = new Teacher();
		teacher.setName("Jan");
		teacher.setBirthdate(LocalDate.of(1990, 11, 13));
		teacher.setEmail("teacher@gmail.com");
		teacher.setSurname("Nowak");
		teacher.setUsername("elson");
		teacher.setPassword("hehe");
        
		Course analiza= new Course();
		analiza.setName("analiza 2.4A");
		analiza.getStudents().add(student);
		analiza.setTeacher(teacher);
		analiza.setStudentsLimit(10);
		teacher.getCoursesTaught().add(analiza);
		student.getCoursesAttended().add(analiza);
		
		Grade grade = new Grade(5.0,false,analiza,student);
		analiza.getGrades().add(grade);
		student.getGrades().add(grade);
		
		userService.insertUser(student);
		
		return "test";
	}

	

}
