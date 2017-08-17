package com.cinek.edziennik.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinek.edziennik.model.Course;
import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.Student;
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
	public List<Course> findCourseByNameQuery(String name) {
		Query query = entityManager.createQuery("select c from Course c where c.name LIKE %:name%");
		query.setParameter("name", name);
		List<Course> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public Course findById(Long id) {
		Course course = entityManager.find(Course.class, id);
		return course;
	}

	@Override
	@Transactional
	public List<Course> findAllCourses() {
		List<Course> results = entityManager.createQuery("Select course from Course course", Course.class)
				.getResultList();
		return results;
	}

	@Override
	public Grade findGradeById(Long gradeId) {
		Grade grade = entityManager.find(Grade.class, gradeId);
		return grade;
	}

	@Override
	/**
	 * Return students grade from specified course,
	 * returns null if student doesn't have a grade yet
	 * @param studentId - id of student
	 * @param courseId - id of course
	 *
	 */
	public Grade findStudentsGradeById(Long studentId, Long courseId) {
		TypedQuery<Grade> query = entityManager
				.createQuery("Select g from Grade g where g.student.id=:sId and g.course.id=:cId", Grade.class);
		query.setParameter("sId", studentId);
		query.setParameter("cId", courseId);
		Grade grade = null;
		try {
			grade = query.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

		return grade;
	}

}
