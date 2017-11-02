package com.cinek.edziennik.repository;

import java.util.List;

import com.cinek.edziennik.model.GradeChangeRequest;

public interface GradeChangeRequestRepository {
   void insert(GradeChangeRequest gradeChangeRequest);
   GradeChangeRequest findById(Long id);
   List<GradeChangeRequest> findByTeacherId(Long id);
   void delete(GradeChangeRequest gradeChangeRequest);
 
   
}
