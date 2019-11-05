package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.services.validations.SubjectInsertAndUpdateValid;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@SubjectInsertAndUpdateValid
public class SubjectDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Parameter name cannot be null")
    @Length(min = 5, max = 80, message = "Name parameter length must be between 5 and 80 characters")
    private String name;

    @NotEmpty(message = "Parameter description cannot be null")
    @Length(min = 5, max = 80, message = "Description parameter length must be between 5 and 80 characters")
    private String description;

    @NotNull(message = "Parameter workload cannot be null")
    private Double workload;

    @NotNull(message = "Parameter active cannot be null")
    private boolean active;

    private Instant creationDate;

    @NotNull(message = "Parameter workload cannot be null")
    private Double minimumScore;

    @NotNull(message = "Parameter startDate cannot be null")
    private LocalDate startDate;

    @NotNull(message = "Parameter endDate cannot be null")
    private LocalDate endDate;

    public SubjectDTO(){}

    public SubjectDTO(Long id, String name, String description, Double workload, boolean active, Instant creationDate, Double minimumScore
    ,LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workload = workload;
        this.active = active;
        this.creationDate = creationDate;
        this.minimumScore = minimumScore;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SubjectDTO(Subject entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.workload = entity.getWorkload();
        this.active = entity.isActive();
        this.creationDate = entity.getCreationDate();
        this.minimumScore = entity.getMinimumScore();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setMinimumScore(Double minimumScore) {
        this.minimumScore = minimumScore;
    }

    public Subject toEntity(){
        return new Subject(id, name, description, workload, active, creationDate, minimumScore,startDate,endDate);
    }
}
