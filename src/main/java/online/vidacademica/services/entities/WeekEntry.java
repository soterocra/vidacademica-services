package online.vidacademica.services.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;

@Entity
@Table(name = "tb_week_entry")
public class WeekEntry implements Serializable, Comparable<WeekEntry> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayOfWeek day;
    private Long startMillisecond;
    private Long endMillisecond;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "classe")
    private Classe classe;

    public WeekEntry() {
    }

    public WeekEntry(Long id, DayOfWeek day, Long startMillisecond, Long endMillisecond, Classe classe) {
        if (startMillisecond >= endMillisecond) {
            throw new IllegalArgumentException("Start time must be less than end time");
        }
        this.id = id;
        this.day = day;
        this.startMillisecond = startMillisecond;
        this.endMillisecond = endMillisecond;
        this.classe = classe;
    }

    public WeekEntry(Long id, DayOfWeek day, Long startHour, Long startMinute, Long endHour, Long endMinute, Classe classe) {
        Long startMillisecond = startHour * 3600000L + startMinute * 60000L;
        Long endMillisecond = endHour * 3600000L + endMinute * 60000L;
        if (startMillisecond >= endMillisecond) {
            throw new IllegalArgumentException("Start time must be less than end time");
        }
        this.id = id;
        this.day = day;
        this.startMillisecond = startMillisecond;
        this.endMillisecond = endMillisecond;
        this.classe = classe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public Long getStartMillisecond() {
        return startMillisecond;
    }

    public Long getEndMillisecond() {
        return endMillisecond;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setStartMillisecond(Long startMillisecond) {
        if (startMillisecond >= getEndMillisecond()) {
            throw new IllegalArgumentException("Start time must be less than end time");
        }
        setStartMillisecond(startMillisecond);
    }

    public void setEndMillisecond(Long endMillisecond) {
        if (getStartMillisecond() >= endMillisecond) {
            throw new IllegalArgumentException("Start time must be less than end time");
        }
        setEndMillisecond(endMillisecond);
    }

    public Long getStartSecond() {
        return getStartMillisecond() / 1000L;
    }

    public Long getEndSecond() {
        return getEndMillisecond() / 1000L;
    }

    public Long getStartHour() {
        return getStartMillisecond() / 3600000L;
    }

    public Long getStartMinute() {
        return getStartMillisecond() % 3600000L;
    }

    public Long getEndHour() {
        return getEndMillisecond() / 3600000L;
    }

    public Long getEndMinute() {
        return getEndMillisecond() % 3600000L;
    }

    public void setStart(Long startHour, Long startMinute) {
        Long startMillisecond = startHour * 3600000L + startMinute * 60000L;
        setStartMillisecond(startMillisecond);
    }

    public void setEnd(Long endHour, Long endMinute) {
        Long endMillisecond = endHour * 3600000L + endMinute * 60000L;
        setEndMillisecond(endMillisecond);
    }

    public boolean conflicts(WeekEntry other) {
        if (getDay() != other.getDay()) {
            return false;
        }
        if (getEndMillisecond() <= other.getStartMillisecond() || getStartMillisecond() >= other.getEndMillisecond()) {
            return false;
        }
        return true;
    }

    public int compareTo(WeekEntry other) {
        if (getDay().compareTo(other.getDay()) == 0) {
            return getStartMillisecond().compareTo(other.getStartMillisecond());
        }
        return getDay().compareTo(other.getDay());
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((endMillisecond == null) ? 0 : endMillisecond.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((startMillisecond == null) ? 0 : startMillisecond.hashCode());
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
        WeekEntry other = (WeekEntry) obj;
        if (day != other.day)
            return false;
        if (endMillisecond == null) {
            if (other.endMillisecond != null)
                return false;
        } else if (!endMillisecond.equals(other.endMillisecond))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (startMillisecond == null) {
            if (other.startMillisecond != null)
                return false;
        } else if (!startMillisecond.equals(other.startMillisecond))
            return false;
        return true;
    }
}
