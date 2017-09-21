package com.cinek.edziennik.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinek.edziennik.exception.NoSuchGradeException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.repository.CourseRepository;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.CourseService;
import com.cinek.edziennik.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	CourseService courseService;

	public StudentServiceImpl(UserRepository userRepository, CourseRepository courseRepository) {
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;

	}

	@Override
	public void acceptGrade(Long gradeId) throws NoSuchGradeException {
		Grade grade = findGradeById(gradeId);
		if (grade != null) {
			grade.setAccepted(true);
		} else {
			throw new NoSuchGradeException();
		}

	}

	private Grade findGradeById(Long gradeId) {
		Grade grade = courseRepository.findGradeById(gradeId);
		return grade;
	}

	@Override
	public double showAverageGrade(Long studentId) {
		double avg = 0;
		int sum = 0;
		Student student = (Student) userRepository.findById(studentId);
		for (Grade g : student.getGrades()) {
			sum += g.getGrade();
		}
		avg = sum / student.getGrades().size();
		return avg;
	}

	@Override
	/** 
	 * returns set of grades for specified student
	 * @param username - username of the student
	 */
	public Set<Grade> getStudentGradesByUsername(String username) {
		Student student = (Student) userRepository.findByUsername(username);
		Set<Grade> grades = student.getGrades();
		Hibernate.initialize(grades);
		return grades;
	}

	@Override
	/**
	 * returns set of courses that are avaible for specified student
	 * to sign in(not full, not already signed by this student)
	 * @param username - username of the student
	 */
	public Set<Course> getCoursesAvaibleToSingIn(String username) {
		Set<Course> avaibleCourses = courseService.getAllCoursesAvaible();
		Student student = (Student) userRepository.findByUsername(username);
		List<Course> coursesAttended = student.getCoursesAttended();
		avaibleCourses.removeAll(coursesAttended);
		return avaibleCourses;
	}

	@Override
	public Grade findStudentsGradeById(Long studentId, Long courseId) {
		Grade grade = null;
		try {
		    grade = courseRepository.findStudentsGradeById(studentId, courseId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return grade;
	}

}
