package com.cinek.edziennik;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cinek.edziennik.exception.StudentNoSuchCourseException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.repository.CourseRepository;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.impl.CourseServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class courseServiceTest {
	@Mock
	private UserRepository userRepository;
	@Mock
	private CourseRepository courseRepository;
	@InjectMocks
	private CourseServiceImpl courseService;

	private Student student;

	private Course course;
	private Teacher teacher;

	@Before
	public void setUp() {
		student = new Student();
		student.setId(new Long(1));
		course = new Course();
		course.setId(new Long(1));
		teacher = new Teacher();
		teacher.setId(new Long(2));

		when(userRepository.findById(new Long(1))).thenReturn(student);
		when(userRepository.findById(new Long(2))).thenReturn(teacher);
		when(courseRepository.findById(new Long(1))).thenReturn(course);

	}

	@Test
	public void shouldAddTeacherToCourse() {
		courseService.addTeacherToCourse(new Long(2), new Long(1));
		assertEquals(teacher.getCoursesTaught().get(0),course);
	    assertEquals(course.getTeacher(),teacher);
	}
    @Test
    public void shouldAddStudentToCourse() {
    	courseService.addStudentToCourse(new Long(1), new Long(1));
    	assertEquals(course.getStudents().get(0),student);
    	assertEquals(student.getCoursesAttended().get(0),course);
    }
    @Test
    public void shouldSetGradeFromCourse() {
    	courseService.addStudentToCourse(new Long(1), new Long(1));
    	try {
			courseService.setGradeFromCourse(new Long(1), new Long(1), 4.5);
		} catch (StudentNoSuchCourseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Grade grade = student.getGrades().iterator().next();
    	assertNotNull(grade);
    	assertTrue(grade.getGrade()==4.5);
    	
    }
}
