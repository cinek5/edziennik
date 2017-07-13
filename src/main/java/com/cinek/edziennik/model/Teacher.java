package com.cinek.edziennik.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Teacher extends User{
	
	@OneToMany(mappedBy="teacher",cascade= CascadeType.ALL)
	private List<Course> coursesTaught;

	public Teacher() {
		super();
		coursesTaught = new ArrayList<Course>();
	}

	public List<Course> getCoursesTaught() {
		return coursesTaught;
	}

	public void setCoursesTaught(List<Course> coursesTaught) {
		this.coursesTaught = coursesTaught;
	}
	
}	
