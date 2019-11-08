package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.Registration;
import online.vidacademica.services.entities.User;

import java.io.Serializable;
import java.time.Instant;

public class RegistrationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Instant date;
    private Long userId;
    private Long classId;

    public RegistrationDTO() {
    }

    public RegistrationDTO(Long id, Instant date, Long classId, Long userId) {
        this.id = id;
        this.classId = classId;
        this.userId = userId;
        setDate(date);
    }

    public RegistrationDTO(Registration entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.classId = entity.getClasse().getId();
        this.userId = entity.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        if (date == null) {
            date = Instant.now();
        }
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Registration toEntity() {
        return new Registration(
                id,
                date,
                new User(userId, null, null, null, null, null, null),
                new Classe(classId, null, null, null, false, null)
        );
    }
}
