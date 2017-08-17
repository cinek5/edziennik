package com.cinek.edziennik.service.impl;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.service.CourseService;
import com.cinek.edziennik.service.UserService;

@Service
@Transactional
public class ExampleEntitesCreatorService {
	@Autowired
	CourseService courseService;
	@Autowired
	UserService userService;
	
	public void createEntites() {
		Teacher teacher1 = new Teacher();
		teacher1.setUsername("teacher1");
		teacher1.setPassword("teacher1");
		teacher1.setEmail("teacher1@dupa.pl");
		teacher1.setBirthdate(LocalDate.of(1998, 1,1));
		teacher1.setName("Teacher");
		teacher1.setSurname("Jeden");
		userService.registerNewTeacher(teacher1);
		Course analiza = new Course();
		analiza.setName("Analiza Mat 1");
		analiza.setTeacher(teacher1);
		analiza.setStudentsLimit(20);
		courseService.insertCourse(analiza);
		
		Course analiza2 = new Course();
		analiza2.setName("Analiza Mat 2");
		analiza2.setTeacher(teacher1);
		analiza2.setStudentsLimit(20);
		courseService.insertCourse(analiza2);
		
		Course prog = new Course();
		prog.setName("Podstawy Programowania");
		prog.setTeacher(teacher1);
		prog.setStudentsLimit(20);
		courseService.insertCourse(prog);
		
		Student student1 = new Student();
		student1.setUsername("student1");
		student1.setPassword("student1");
		student1.setEmail("student1@dupa.pl");
		student1.setBirthdate(LocalDate.of(1998, 1,1));
		student1.setName("Student");
		student1.setSurname("Jeden");
		userService.registerNewStudent(student1);
		
		Student student2 = new Student();
		student2.setUsername("student2");
		student2.setPassword("student2");
		student2.setEmail("student2@dupa.pl");
		student2.setBirthdate(LocalDate.of(1998, 1,1));
		student2.setName("Student");
		student2.setSurname("Dwa");
		userService.registerNewStudent(student2);
		
		Student student3 = new Student();
		student3.setUsername("student3");
		student3.setPassword("student3");
		student3.setEmail("student3@dupa.pl");
		student3.setBirthdate(LocalDate.of(1998, 1,1));
		student3.setName("Student");
		student3.setSurname("Trzy");
		userService.registerNewStudent(student3);
		
		
	}
	
}
