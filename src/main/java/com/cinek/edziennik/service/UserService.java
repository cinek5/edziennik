package com.cinek.edziennik.service;

import java.util.List;

import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.model.User;
import com.cinek.edziennik.model.ProfilePictureFile;
public interface UserService {
	User findByUsername(String username);

	void insertUser(User user);

	List<Teacher> getAllTeachers();

	User findById(Long id);

	void registerNewTeacher(Teacher teacher);
	
	void registerNewStudent(Student student);
	 List<Student> searchStudentBySurname(String surname);
	 List<Student> searchStudentBySurnameAttendingCourse(String surname,Long courseId);
    void addProfilePictureToUser(User user, ProfilePictureFile profilPic);

	


}
