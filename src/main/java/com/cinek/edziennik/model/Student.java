package com.cinek.edziennik.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Student extends User {

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Course> coursesAttended;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private Set<Grade> grades;

	public Student() {
		super();
		coursesAttended = new ArrayList<Course>();
		grades  = new HashSet<Grade>();
		
	}

	public List<Course> getCoursesAttended() {
		return coursesAttended;
	}

	public void setCoursesAttended(List<Course> coursesAttended) {
		this.coursesAttended = coursesAttended;
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

}
