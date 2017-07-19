package com.cinek.edziennik.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinek.edziennik.exception.NoSuchGradeException;
import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.repository.CourseRepository;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CourseRepository courseRepository;
    public StudentServiceImpl(UserRepository userRepository, CourseRepository courseRepository) {
    	this.userRepository=userRepository;
    	this.courseRepository=courseRepository;
    	
    }
	@Override
	public void acceptGrade(Long studentId, Long gradeId) throws NoSuchGradeException {
		Student student = (Student) userRepository.findById(studentId);
        Grade grade = findStudentsGradeById(student,gradeId);
        if (grade!=null) {
        grade.setAccepted(true);
        } else {
        	throw new NoSuchGradeException();
        }
       
        
	}

	private Grade findStudentsGradeById(Student student, Long gradeId) {
		Grade grade = null;
		for (Grade g : student.getGrades()) {
			if (g.getId().equals(gradeId)) {
				grade = g;
			}
		}
		return grade;
	}

	@Override
	public double showAverageGrade(Long studentId) {
		double avg = 0;
		int sum=0;
		Student student = (Student) userRepository.findById(studentId);
		for (Grade g:student.getGrades()) {
			sum+=g.getGrade();
		}
		avg = sum/student.getGrades().size();
		return avg;
	}

}
