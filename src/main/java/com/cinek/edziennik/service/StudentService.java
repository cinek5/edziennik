package com.cinek.edziennik.service;

import com.cinek.edziennik.exception.NoSuchGradeException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Student;

public interface StudentService {
	void acceptGrade(Long studentId,Long gradeId) throws NoSuchGradeException;
	double showAverageGrade(Long studentId);
	
}
