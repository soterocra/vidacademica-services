package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Test;

import java.io.Serializable;
import java.time.Instant;

public class TestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String name;
    private Double fullScore;
    private Instant date;
    private Instant creationDate;

    public TestDTO() {
    }

    public TestDTO(Long id, String name, Double fullScore, Instant date, Instant creationDate) {
        this.id = id;
        this.name = name;
        this.fullScore = fullScore;
        this.date = date;
        this.creationDate = creationDate;
    }

    public TestDTO(Test entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.fullScore = entity.getFullScore();
        this.date = entity.getDate();
        this.creationDate = entity.getCreationDate();
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

    public Double getFullScore() {
        return fullScore;
    }

    public void setFullScore(Double fullScore) {
        this.fullScore = fullScore;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Test toEntity() {
        return new Test(id, name, fullScore, date, creationDate);
    }
}
