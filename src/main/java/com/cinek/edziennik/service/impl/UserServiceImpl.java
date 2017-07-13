package com.cinek.edziennik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.model.User;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

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

}
