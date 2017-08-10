package com.cinek.edziennik.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Course {

	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@Size(min = 3)
	private String name;
	@ManyToOne(cascade = CascadeType.ALL)
	private Teacher teacher;
	@ManyToMany(mappedBy = "coursesAttended", cascade = CascadeType.ALL)
	private List<Student> students;
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Set<Grade> grades;
	@Min(10)
	private Integer studentsLimit;
	

	public Course() {
		super();
		students = new ArrayList<Student>();
		grades = new HashSet<Grade>();
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Integer getStudentsLimit() {
		return studentsLimit;
	}

	public void setStudentsLimit(Integer studentsLimit) {
		this.studentsLimit = studentsLimit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public boolean isFull() {
		return students.size()>=studentsLimit;
	}
	public int getSize() {
		return students.size();
	}

}
