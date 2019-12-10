package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Registration;
import online.vidacademica.services.entities.User;
import online.vidacademica.services.services.validations.UserUpdateValid;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@UserUpdateValid
public class UserDTO {

    private Long id;

    @NotEmpty(message = "Parameter name cannot be null")
    @Length(min = 5, max = 80, message = "Name parameter length must be between 5 and 80 characters")
    private String name;

    @NotEmpty(message = "Parameter email cannot be null")
    @Email(message = "Parameter email invalid")
    private String email;

    @NotNull(message = "Parameter dateOfBirth cannot be null")
    private Instant dateOfBirth;

    @NotEmpty(message = "Parameter socialId cannot be null")
    private String socialId;

//    @NotEmpty(message = "Parameter registration cannot be null")
    private Registration registration;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, Instant dateOfBirth, String socialId, Registration registration) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.socialId = socialId;
        this.registration = registration;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.dateOfBirth = entity.getDateOfBirth();
        this.socialId = entity.getSocialId();
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

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public User toEntity() {
        return new User(null, name, email, dateOfBirth, socialId, null, null);
    }

}
