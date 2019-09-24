package online.vidacademica.services.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import online.vidacademica.services.entities.pk.AttendancePK;

@Entity
@Table(name = "tb_attendance")
public class Attendance implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AttendancePK id = new AttendancePK();
	
	private Instant date;
	private boolean present;
	
	public Attendance() {}

	public Attendance(AttendancePK id, Instant date, boolean present) {
		super();
		this.id = id;
		this.date = date;
		this.present = present;
	}

	public AttendancePK getId() {
		return id;
	}

	public void setId(AttendancePK id) {
		this.id = id;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}
	
	@JsonIgnore
	public Student getStudent() {
		return id.getStudent();
	}
	
	public void setStudent(Student student) {
		id.setStudent(student);		
	}
	
	public Registration getRegistration() {
		return id.getRegistration();
	}
	
	public void setRegistration(Registration registration) {
		id.setRegistration(registration);
	}

}
