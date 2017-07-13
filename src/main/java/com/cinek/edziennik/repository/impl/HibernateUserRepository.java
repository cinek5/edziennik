package com.cinek.edziennik.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.model.User;
import com.cinek.edziennik.repository.UserRepository;
@Repository
public class HibernateUserRepository implements UserRepository {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public User findByUsername(String userName) {

		Query query = entityManager.createQuery("select u from User u where u.username= :name");
		query.setParameter("name", userName);
		User result = (User) query.getSingleResult();

		return result;
	}

	@Override
	@Transactional
	public void insertUser(User user) {
		entityManager.persist(user);
		
	}

	@Override
	@Transactional
	public User findById(Long id) {
		User user = (User) entityManager.find(User.class, id);
		return user;
	}

	@Override
	@Transactional
	public List<Teacher> getAllTeachers() {
		Query query = entityManager.createQuery("select t from Teacher t");
		List<Teacher> result = query.getResultList();
		return result;
	}

	

	

	
	

	
	

}
