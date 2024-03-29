package online.vidacademica.services.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double workload;
    private boolean active;
    private Instant creationDate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_subject_course", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subject = new HashSet<>();

    @ManyToOne
    private User commander;

    public Course() {
    }

    public Course(Long id, String name, String description, Double workload, boolean active, Instant creationDate) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.workload = workload;
        this.active = active;
        this.creationDate = creationDate;
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

    public User getCommander() {
        return commander;
    }

    public void setCommander(User commander) {
        this.commander = commander;
    }

    public Set<Subject> getSubjects() {
        return subject;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
