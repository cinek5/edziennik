package com.cinek.edziennik.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.repository.CourseRepository;
@Repository
public class HibernateCourseRepository implements CourseRepository {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public void insertCourse(Course course) {
		entityManager.persist(course);
	}

	@Override
	@Transactional
	public void insertGrade(Grade grade) {
		entityManager.persist(grade);

	}

	@Override
	@Transactional
	public  List<Course> findCourseByNameQuery(String name) {
		 Query query = entityManager.createQuery("select c from Course c where c.name LIKE %:name%");
		    query.setParameter("name", name);
		    List<Course> result =  query.getResultList();
			return result;
	}

	@Override
	@Transactional
	public Course findById(Long id) {
		Course course = entityManager.find(Course.class, id);
		return course;
	}

}
