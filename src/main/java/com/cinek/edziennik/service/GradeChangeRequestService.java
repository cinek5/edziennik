package com.cinek.edziennik.service;

import java.util.List;

import com.cinek.edziennik.model.GradeChangeRequest;

public interface GradeChangeRequestService {
	void createNewRequest(Long teacherId, Long studentId, Long gradeId, Double requestedGrade);
	void accept(Long gradeChangeRequestId);
	void delete(Long gradeChangeRequestId);
	void insert(GradeChangeRequest gradeChangeRequest);
    GradeChangeRequest findById(Long id);
    List<GradeChangeRequest> findByTeacherId(Long id);
}
