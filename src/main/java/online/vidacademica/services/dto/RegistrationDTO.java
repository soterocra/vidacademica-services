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
    private String user;
    private String classe;

    public RegistrationDTO() {
    }

    public RegistrationDTO(Long id, Instant date, String classe, String user) {
        this.id = id;
        this.classe = classe;
        this.user = user;
        setDate(date);
    }

    public RegistrationDTO(Registration entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.classe = entity.getClasse().getName();
        this.user = entity.getUser().getName();
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

    public String getUser() {
        return user;
    }

    public void setUser(String userId) {
        this.user= userId;
    }

    public String getClasse() {
        return classe;
    }

    public void setClass(String classe) {
        this.classe = classe;
    }

    public Registration toEntity() {
        return new Registration(
                id,
                date,
                new User(null, user, null, null, null, null, null),
                new Classe(null, classe, null, null, false, null)
        );
    }
}
