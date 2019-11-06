package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Registration;

import java.io.Serializable;
import java.time.Instant;

public class RegistrationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Instant date;
    private boolean approved;

    public RegistrationDTO(){}

    public RegistrationDTO(Long id, Instant date, boolean approved) {
        this.id = id;
        this.date = date;
        this.approved = approved;
    }

    public RegistrationDTO(Registration entity){
        this.id = entity.getId();
        this.date = entity.getDate();
        this.approved = entity.isApproved();
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
        this.date = date;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Registration toEntity() {
        return new Registration(id, date, approved);
    }
}
