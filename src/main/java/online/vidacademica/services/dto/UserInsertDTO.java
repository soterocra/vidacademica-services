package online.vidacademica.services.dto;

import online.vidacademica.services.entities.User;
import online.vidacademica.services.services.validations.UserInsertValid;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@UserInsertValid
public class UserInsertDTO {

	private Long id;

	@NotEmpty(message = "Parameter name cannot be null")
	@Length(min=5,max=80,message = "Name parameter length must be between 5 and 80 characters")
	private String name;

	@NotEmpty(message = "Parameter email cannot be null")
	@Email(message = "Parameter email invalid")
    private String email;

	@NotNull(message = "Parameter dateOfBirth cannot be null")
	private Instant dateOfBirth;

	@NotEmpty(message = "Parameter socialId cannot be null")
	private String socialId;

	@NotEmpty(message = "Parameter registration cannot be null")
	private String registration;

	@NotEmpty(message = "Parameter dateOfBirth cannot be null")
	@Length(min=8,message = "Password length must be at least 8 characters")
	private String password;
    
    public UserInsertDTO () {}

	public UserInsertDTO(Long id, String name, String email, Instant dateOfBirth, String socialId, String registration, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.socialId = socialId;
		this.registration = registration;
		this.password = password;
	}
    
    public UserInsertDTO(User entity) {
    	this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.dateOfBirth = entity.getDateOfBirth();
		this.socialId = entity.getSocialId();
		this.registration = entity.getRegistration();
		this.password = entity.getPassword();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Instant dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User toEntity(){
		return new User(id, name, email, dateOfBirth, socialId, registration, password, null);
	}
}
