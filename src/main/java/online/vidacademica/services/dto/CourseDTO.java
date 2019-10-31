package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Course;

import java.io.Serializable;
import java.time.Instant;

public class CourseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Double workload;
    private boolean active;
    private Instant creationDate;

    public CourseDTO(){}

    public CourseDTO(Long id, String name, String description, Double workload, boolean active, Instant creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workload = workload;
        this.active = active;
        this.creationDate = creationDate;
    }

    public CourseDTO(Course entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.workload = entity.getWorkload();
        this.active = entity.isActive();
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

    public Course toEntity(){
        return new Course(id, name, description, workload, active, creationDate);
    }
}
