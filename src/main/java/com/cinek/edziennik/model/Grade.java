package com.cinek.edziennik.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Grade {
	/*
	 * teacher-who set the grade
	 * 
	 */
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL)
	private Student student;
	@ManyToOne(cascade = CascadeType.ALL)
	private Course course;
	private double grade;
	private boolean accepted;

	public Grade() {
		super();
	}

	public Grade(double grade, boolean accepted,Course course, Student student) {
		this.grade = grade;
		this.accepted = accepted;
		this.course=course;
		this.student=student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

}
