package com.cinek.edziennik.repository;



import java.util.List;

import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.model.User;

public interface UserRepository {
	User findByUsername(String userName);
	User findById(Long id);
	void insertUser(User user);
    List<Teacher> getAllTeachers();
    List<Student> getAllStudents();
    List<User> getAllUsers();
    List<Student> searchStudentBySurname(String surname);
    List<Student> searchStudentBySurnameAttendingCourse(String surname,Long courseId);
	void merge(User user);
	
}
