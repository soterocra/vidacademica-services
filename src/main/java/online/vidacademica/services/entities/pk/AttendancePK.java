package online.vidacademica.services.entities.pk;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import online.vidacademica.services.entities.Registration;
import online.vidacademica.services.entities.Student;



public class AttendancePK  implements Serializable {

			private static final long serialVersionUID = 1L;
			
			@ManyToOne
			@JoinColumn(name = "student_id")
			private Student student;
			
		
			@OneToOne
			@JoinColumn(name = "registration_id")
			private Registration registration;
			
			
			public Student getStudent() {
				return student;
			}
			public void setStudent(Student student) {
				this.student = student;
			}
			public Registration getRegistration() {
				return registration;
			}
			public void setRegistration(Registration registration) {
				this.registration = registration;
			}
}
