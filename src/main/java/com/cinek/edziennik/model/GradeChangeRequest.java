package com.cinek.edziennik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class GradeChangeRequest {

	@GeneratedValue
	@Id
	private Long id;
	@OneToOne
	private Grade grade;
	private Double requestedGrade;
	private boolean isDecisionMade;
	@ManyToOne
	private Student student;
	@ManyToOne
	private Teacher teacher;

	public GradeChangeRequest() {
		super();
		isDecisionMade = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getRequestedGrade() {
		return requestedGrade;
	}

	public void setRequestedGrade(Double requestedGrade) {
		this.requestedGrade = requestedGrade;
	}

	public boolean isDecisionMade() {
		return isDecisionMade;
	}

	public void setDecisionMade(boolean isDecisionMade) {
		this.isDecisionMade = isDecisionMade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
