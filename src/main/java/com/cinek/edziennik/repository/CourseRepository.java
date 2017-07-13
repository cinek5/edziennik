package com.cinek.edziennik.repository;

import java.util.List;

import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;

public interface CourseRepository {
	Course findById(Long id);
	void insertCourse(Course course);
	void insertGrade(Grade grade);
	List<Course> findCourseByNameQuery(String query);
	
}
