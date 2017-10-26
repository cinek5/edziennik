package com.cinek.edziennik.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
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
	public void merge(User user) {
		entityManager.merge(user);
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

	

	@Override
	public List<Student> searchStudentBySurname(String surname) {
		TypedQuery<Student> query = entityManager
				.createQuery("Select s from Student s  where s.surname like :surname", Student.class);
		query.setParameter("surname", "%"+surname+"%");
		List<Student> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	/**
	 * Return list of students attending specific course whose surname is substring of parameter
	 * @param courseId - id of course
	 * @param surname - query surname 
	 */
	public List<Student> searchStudentBySurnameAttendingCourse(String surname, Long courseId) {
		TypedQuery<Student> query = entityManager
				.createQuery("Select s from Student s join s.coursesAttended course where s.surname like :surname and course.id=:cId", Student.class);
		query.setParameter("surname", "%"+surname+"%");
		query.setParameter("cId", courseId);
		List<Student> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		TypedQuery<User> query = entityManager.createQuery("select u from User u",User.class);
		List<User> result = query.getResultList();
		Hibernate.initialize(result);
		return result;
	}

	@Override
	public List<Student> getAllStudents() {
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s",Student.class);
		List<Student> result = query.getResultList();
		return result;
		
	}
	

	

	

	
	

	
	

}
