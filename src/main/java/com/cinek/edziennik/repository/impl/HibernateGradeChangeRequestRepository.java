package com.cinek.edziennik.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cinek.edziennik.model.GradeChangeRequest;
import com.cinek.edziennik.repository.GradeChangeRequestRepository;
@Repository
public class HibernateGradeChangeRequestRepository implements GradeChangeRequestRepository	 {
	
	@PersistenceContext
	EntityManager entityManager;
	@Override
	public void insert(GradeChangeRequest gradeChangeRequest) {
		entityManager.persist(gradeChangeRequest);
		
	}

	@Override
	public GradeChangeRequest findById(Long id) {
		GradeChangeRequest result = null;
		result = entityManager.find(GradeChangeRequest.class, id);
	    return result;
	}

	@Override
	public List<GradeChangeRequest> findByTeacherId(Long id) {
		List<GradeChangeRequest> result = null;
		TypedQuery<GradeChangeRequest> query = entityManager.createQuery(
				"select changeReq from GradeChangeRequest changeReq where changeReq.teacher.id=:tId", GradeChangeRequest.class);
		query.setParameter("tId", id);
		result = query.getResultList();
		return result;
	}

	@Override
	public void delete(GradeChangeRequest gradeChangeRequest) {
		entityManager.remove(gradeChangeRequest);
		
	}

}
