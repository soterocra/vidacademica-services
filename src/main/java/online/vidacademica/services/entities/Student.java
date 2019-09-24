package online.vidacademica.services.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student {
	
	@OneToMany(mappedBy = "id.student")
	private Set<Attendance> attendances = new HashSet<>();
	
	@JsonIgnore
	public Set<Registration> getRegistrations() {
		Set<Registration> set = new HashSet<>();
		for (Attendance x :attendances) {
			set.add(x.getRegistration());
		}

		return set;
	}

	
}
