package com.cinek.edziennik.service;

import java.util.List;

import com.cinek.edziennik.exception.StudentNoSuchCourseException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;

public interface CourseService {
	Course findById(Long id);
	void addStudentToCourse(Long studentId, Long courseId);
	void addTeacherToCourse(Long teacherId,Long courseId);
	void setGradeFromCourse(Long studentId,Long courseId,double grade) throws StudentNoSuchCourseException;
	List<Course> getCoursesStudentAttends(String username);
	List<Course> getCoursesTeacherTeaches(String username);
	void getAllCoursesAvaible();
	void insertCourse(Course course);
	
}
