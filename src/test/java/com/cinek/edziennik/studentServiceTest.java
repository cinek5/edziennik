package com.cinek.edziennik;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cinek.edziennik.exception.NoSuchGradeException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.repository.CourseRepository;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.StudentService;
import com.cinek.edziennik.service.impl.StudentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class studentServiceTest {
	@Mock
	private UserRepository userRepository;
	@Mock
	private CourseRepository courseRepository;
	@InjectMocks
	private StudentServiceImpl studentService;
	private Student student;
	private Course course;
	private Grade grade;

	@Before
	public void setUp() {

		student = new Student();
		student.setId(new Long(1));
		course = new Course();
		course.setId(new Long(1));
		student.getCoursesAttended().add(course);
		course.getStudents().add(student);
		grade = new Grade();
		grade.setId(new Long(1));
		grade.setAccepted(false);
		grade.setStudent(student);
		grade.setCourse(course);
		grade.setGrade(5.0);
		course.getGrades().add(grade);
		student.getGrades().add(grade);
		when(userRepository.findById(new Long(1))).thenReturn(student);
		when(courseRepository.findById(new Long(2))).thenReturn(course);
		
		
	}

	@Test
	public void shouldSGradeAccepted() {
		try {
			studentService.acceptGrade(new Long(1));
		} catch (NoSuchGradeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(grade.isAccepted());

	}
	@Test 
	public void shouldAverageGrade() {
		grade.setAccepted(true);
		assertEquals(5.0,studentService.averageGrade(student.getId()),1e-8);
		
	}

}
