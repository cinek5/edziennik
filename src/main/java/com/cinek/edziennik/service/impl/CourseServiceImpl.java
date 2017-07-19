package com.cinek.edziennik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinek.edziennik.exception.StudentNoSuchCourseException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.model.User;
import com.cinek.edziennik.repository.CourseRepository;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CourseRepository courseRepository;

	@Override
	@Transactional
	public void addStudentToCourse(Long studentId, Long courseId) {
		Student student = (Student) userRepository.findById(studentId);
		Course course = courseRepository.findById(courseId);
		student.getCoursesAttended().add(course);
		course.getStudents().add(student);

	}

	@Override
	@Transactional
	public void addTeacherToCourse(Long teacherId, Long courseId) {
		Teacher teacher = (Teacher) userRepository.findById(teacherId);
		Course course = courseRepository.findById(courseId);
		course.setTeacher(teacher);
		teacher.getCoursesTaught().add(course);

	}

	@Override
	@Transactional
	public void setGradeFromCourse(Long studentId, Long courseId, double grade) throws StudentNoSuchCourseException {
		Student student = (Student) userRepository.findById(studentId);
		Course course = courseRepository.findById(courseId);
		if (!student.getCoursesAttended().contains(course))
			throw new StudentNoSuchCourseException();
		Grade ocena = new Grade(grade, false, course, student);
		ocena.setCourse(course);
		ocena.setStudent(student);
		student.getGrades().add(ocena);
		course.getGrades().add(ocena);
	}

	@Override
	@Transactional
	public List<Course> getCoursesStudentAttends(String username) {
		Student student = (Student) userRepository.findByUsername(username);
		return student.getCoursesAttended();
	}

	@Override
	@Transactional
	public List<Course> getCoursesTeacherTeaches(String username) {
		Teacher teacher = (Teacher) userRepository.findByUsername(username);
		return teacher.getCoursesTaught();
	}

	@Override
	@Transactional
	public void getAllCoursesAvaible() {
		// TODO

	}

	@Override
	@Transactional
	public Course findById(Long id) {

		return courseRepository.findById(id);
	}

	@Override
	@Transactional
	public void insertCourse(Course course) {
		courseRepository.insertCourse(course);
	}

}
