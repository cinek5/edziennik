package com.cinek.edziennik.service.impl;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinek.edziennik.exception.NoSuchGradeException;
import com.cinek.edziennik.helpers.StudentAvgGradesComparator;
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
	public double averageGrade(Long studentId) {
		double avg = 0;
		int sum = 0;
		int numOfAcceptedGrades=0;
		Student student = (Student) userRepository.findById(studentId);
		for (Grade g : student.getGrades()) {
			if (g.isAccepted()) {
			sum += g.getGrade();
			numOfAcceptedGrades += 1;
			}
		}
		if (numOfAcceptedGrades>0)
		avg = sum / numOfAcceptedGrades;
	
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

	@Override
	public Map<Student,Double> getStudentsMapSortedByGrades() {
		List<Student> studentsList = userRepository.getAllStudents();
	    SortedMap<Student,Double> studentsMap = new TreeMap<Student, Double>(new StudentAvgGradesComparator());
		for (Student s: studentsList) {
			double avg = s.averageGrade();
			if (avg>0)
			studentsMap.put(s,avg );
		}
		return studentsMap;
	}

}
