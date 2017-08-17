package com.cinek.edziennik.service;

import java.util.Set;

import com.cinek.edziennik.exception.NoSuchGradeException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;

public interface StudentService {
	void acceptGrade(Long gradeId) throws NoSuchGradeException;
	double showAverageGrade(Long studentId);
	Set<Grade> getStudentGradesByUsername(String username);
	Set<Course> getCoursesAvaibleToSingIn(String username);
    Grade findStudentsGradeById(Long studentId,Long courseId);
	
}
