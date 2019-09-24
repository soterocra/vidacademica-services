package online.vidacademica.services.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Teacher {
	
	@JsonIgnore
	@OneToMany(mappedBy = "teacher")
	private Set<Subject> subjects = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "teachers")
	private Set<Course> courses = new HashSet<>();
	

	public Set<Subject> getSubjects() {
		return subjects;
	}
	
	public Set<Course> getCourses(){
		return courses;
	}
}
