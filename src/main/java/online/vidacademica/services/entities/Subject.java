package online.vidacademica.services.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double workload;
    private boolean active;
    private Instant creationDate;
    private Double minimumScore;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private Set<Classe> classes = new HashSet<>();


    @ManyToMany(mappedBy = "subject")
    private Set<Course> course = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_subject", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> user = new HashSet<>();


    public Subject() {
    }

    public Subject(Long id, String name, String description, Double workload, boolean active, Instant creationDate,
                   Double minimumScore) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.workload = workload;
        this.active = active;
        this.creationDate = creationDate;
        this.minimumScore = minimumScore;
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

    public Set<Classe> getClasses() {
        return classes;
    }

    public Set<Course> getCourses() {
        return course;
    }

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
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
        Subject other = (Subject) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
