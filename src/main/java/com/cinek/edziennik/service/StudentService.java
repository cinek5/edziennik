package com.cinek.edziennik.service;

import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Student;

public interface StudentService {
	void acceptGrade(Student student,Course course);
	double showAverageGrade(Student student);
	
}
