package com.cinek.edziennik.helpers;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;

import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.service.StudentService;

public class StudentAvgGradesComparator implements Comparator<Student> {
	@Autowired
    StudentService studentService;

	@Override
	public int compare(Student stud2, Student stud1) {
		
		return Double.compare(stud1.averageGrade(),stud2.averageGrade());
	}
}
