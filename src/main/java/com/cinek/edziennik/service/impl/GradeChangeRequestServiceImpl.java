package com.cinek.edziennik.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinek.edziennik.model.Grade;
import com.cinek.edziennik.model.GradeChangeRequest;
import com.cinek.edziennik.model.Student;
import com.cinek.edziennik.model.Teacher;
import com.cinek.edziennik.repository.CourseRepository;
import com.cinek.edziennik.repository.GradeChangeRequestRepository;
import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.service.GradeChangeRequestService;

@Transactional
@Service
public class GradeChangeRequestServiceImpl implements GradeChangeRequestService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	GradeChangeRequestRepository gradeChangeRequestRepository;
	private static final Logger logger = LoggerFactory.getLogger(GradeChangeRequestServiceImpl.class);
	@Override
	public void createNewRequest(Long teacherId, Long studentId, Long gradeId, Double requestedGrade) {

		Grade grade = courseRepository.findGradeById(gradeId);
		if (grade != null && !grade.isAccepted()) {
			Teacher teacher = (Teacher) userRepository.findById(teacherId);
			Student student = (Student) userRepository.findById(studentId);
			GradeChangeRequest changeRequest = new GradeChangeRequest();
			changeRequest.setGrade(grade);
			changeRequest.setDecisionMade(false);
			changeRequest.setStudent(student);
			changeRequest.setTeacher(teacher);
			changeRequest.setRequestedGrade(requestedGrade);
			teacher.getGradeChangeRequests().add(changeRequest);
			student.getGradeChangeRequests().add(changeRequest);

			gradeChangeRequestRepository.insert(changeRequest);
			
			logger.info("New change request has been succesfully created");
		} else {
			logger.info("Error: can't  request accepted grade to be changed");
		}

	}

	@Override
	public void accept(Long gradeChangeRequestId) {
		GradeChangeRequest changeRequest = gradeChangeRequestRepository.findById(gradeChangeRequestId);
		double requestGrade = changeRequest.getRequestedGrade();
		changeRequest.getGrade().setGrade(requestGrade);
		changeRequest.setDecisionMade(true);
		gradeChangeRequestRepository.delete(changeRequest);
		logger.info("Grade change request has been accepted");

	}

	@Override
	public void delete(Long gradeChangeRequestId) {
		GradeChangeRequest changeRequest = gradeChangeRequestRepository.findById(gradeChangeRequestId);
		changeRequest.setDecisionMade(true);
		gradeChangeRequestRepository.delete(changeRequest);
		logger.info("Grade change request has been deleted");

	}

	@Override
	public void insert(GradeChangeRequest gradeChangeRequest) {
		gradeChangeRequestRepository.insert(gradeChangeRequest);
		
	}

	@Override
	public GradeChangeRequest findById(Long id) {

		return gradeChangeRequestRepository.findById(id);
	}

	@Override
	public List<GradeChangeRequest> findByTeacherId(Long id) {
		return gradeChangeRequestRepository.findByTeacherId(id);
	}

}
