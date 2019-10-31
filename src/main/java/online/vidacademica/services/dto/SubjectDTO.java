package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Subject;

import java.io.Serializable;
import java.time.Instant;

public class SubjectDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Double workload;
    private boolean active;
    private Instant creationDate;
    private Double minimumScore;

    public SubjectDTO(){}

    public SubjectDTO(Long id, String name, String description, Double workload, boolean active, Instant creationDate, Double minimumScore) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workload = workload;
        this.active = active;
        this.creationDate = creationDate;
        this.minimumScore = minimumScore;
    }

    public SubjectDTO(Subject entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.workload = entity.getWorkload();
        this.active = entity.isActive();
        this.creationDate = entity.getCreationDate();
        this.minimumScore = entity.getMinimumScore();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWorkload() {
        return workload;
    }

    public void setWorkload(Double workload) {
        this.workload = workload;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Double getMinimumScore() {
        return minimumScore;
    }

    public void setMinimumScore(Double minimumScore) {
        this.minimumScore = minimumScore;
    }

    public Subject toEntity(){
        return new Subject(id, name, description, workload, active, creationDate, minimumScore);
    }
}
