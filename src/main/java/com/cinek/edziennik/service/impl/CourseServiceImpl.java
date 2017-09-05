package com.cinek.edziennik.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinek.edziennik.exception.StudentNoSuchCourseException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
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
	/**
	 * Sets students grade from course. If students already has grade from this
	 * course, it changes the double value of the grade. Otherwise, it creates
	 * the new Grade and adds it to Database;
	 * 
	 * @param studentId
	 *            - id of student
	 * @param courseId
	 *            - id of course
	 * @param grade
	 *            - double value of the grade eg.(4.5,5.0, etc)
	 *
	 */
	public void setGradeFromCourse(Long studentId, Long courseId, double grade) throws StudentNoSuchCourseException {
		Student student = (Student) userRepository.findById(studentId);
		Course course = courseRepository.findById(courseId);
		if (!student.getCoursesAttended().contains(course))
			throw new StudentNoSuchCourseException();
		Grade ocena = courseRepository.findStudentsGradeById(studentId, courseId);
		if (ocena == null) {
			ocena = new Grade(grade, false, course, student);
			ocena.setCourse(course);
			ocena.setStudent(student);
			student.getGrades().add(ocena);
			course.getGrades().add(ocena);
		}
		// accepted grade can't be changed
		else if (!ocena.isAccepted()){
			ocena.setGrade(grade);
		}
	}

	@Override
	@Transactional
	public List<Course> getCoursesStudentAttends(String username) {
		Student student = (Student) userRepository.findByUsername(username);
		List<Course> courses = student.getCoursesAttended();
		Hibernate.initialize(courses);
		return courses;
	}

	@Override
	@Transactional
	public Map<Course, Integer> getCoursesTeacherTeachesWithSize(String username) {
		Teacher teacher = (Teacher) userRepository.findByUsername(username);
		List<Course> courses = teacher.getCoursesTaught();
		Map<Course, Integer> map = new HashMap<Course, Integer>();
		for (Course c : courses) {
			map.put(c, c.getSize());
		}

		return map;
	}

	@Override
	@Transactional
	public Set<Course> getAllCoursesAvaible() {
		List<Course> allCourses = courseRepository.findAllCourses();
		Set<Course> avaibleCourses = new HashSet<Course>();
		for (Course c : allCourses) {
			if (!c.isFull()) {
				avaibleCourses.add(c);
			}
		}

		return avaibleCourses;

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

	@Override
	@Transactional
	public List<Student> getStudentsAttendingCourse(Long courseId) {
		Course course = courseRepository.findById(courseId);
		List<Student> students = course.getStudents();
		Hibernate.initialize(students);
		return students;
	}
	
}
