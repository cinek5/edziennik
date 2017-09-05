package com.cinek.edziennik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.model.User;
import com.cinek.edziennik.model.UserRole;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(Long studentId) {

		return userRepository.findById(studentId);
	}

	@Override
	public void insertUser(User user) {
		userRepository.insertUser(user);

	}

	@Override
	public List<Teacher> getAllTeachers() {
		// TODO Auto-generated method stub
		return userRepository.getAllTeachers();
	}

	@Override
	public void registerNewStudent(Student student) {
		String password = student.getPassword();
		student.setPassword(passwordEncoder.encode(password));
		student.setEnabled(true);
		UserRole role = new UserRole();
		role.setRole("ROLE_STUDENT");
		role.setUser(student);
		student.getUserRole().add(role);
		
		userRepository.insertUser(student);

	}

	@Override
	public void registerNewTeacher(Teacher teacher) {
		String password = teacher.getPassword();
		teacher.setPassword(passwordEncoder.encode(password));
		teacher.setEnabled(true);
		UserRole role = new UserRole();
		role.setRole("ROLE_TEACHER");
		role.setUser(teacher);
		teacher.getUserRole().add(role);

		userRepository.insertUser(teacher);

	}

	@Override
	public List<Student> searchStudentBySurname(String surname) {
		
		return userRepository.searchStudentBySurname(surname);
	}

	@Override
	public List<Student> searchStudentBySurnameAttendingCourse(String surname, Long courseId) {
		
		return userRepository.searchStudentBySurnameAttendingCourse(surname, courseId);
	}

}
