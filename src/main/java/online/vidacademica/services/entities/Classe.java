package online.vidacademica.services.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_classe")
public class Classe implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private Instant creationDate;

    @ManyToOne
    private Subject subject;

    @JsonIgnore
    @OneToMany(mappedBy = "classe")
    private List<Test> test = new ArrayList<>();

    @OneToMany(mappedBy = "classe")
    private List<WeekEntry> weekEntries = new ArrayList<>();

    public Classe() {
    }

    public Classe(Long id, String name, LocalDate startDate, LocalDate endDate, boolean active, Instant creationDate) {
        super();
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Test> getTest() {
        return test;
    }

    public void setTests(List<Test> tests) {
        this.test = tests;
    }

    public List<WeekEntry> getWeekEntries() {
        return weekEntries;
    }

    public void addEntry(WeekEntry entry) {
        validadeNoConflicts(entry);
        weekEntries.add(entry);
    }

    private void validadeNoConflicts(WeekEntry entry) {
        for (WeekEntry e : weekEntries) {
            if (e.conflicts(entry)) {
                throw new IllegalArgumentException("The entry conflicts with timetable");
            }
        }
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
        Classe other = (Classe) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
