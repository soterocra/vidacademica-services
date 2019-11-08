package online.vidacademica.services.dto;

import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.WeekEntry;

import java.io.Serializable;
import java.time.DayOfWeek;

public class WeekEntryDTO implements Serializable {
    private static final long serialVersionUID = -2136207553191239721L;

    private Long id;
    private DayOfWeek day;
    private Long startMillisecond;
    private Long endMillisecond;
    private Long classeId;

    public WeekEntryDTO() {
    }

    public WeekEntryDTO(Long id, DayOfWeek day, Long startMillisecond, Long endMillisecond) {
        this.id = id;
        this.day = day;
        this.startMillisecond = startMillisecond;
        this.endMillisecond = endMillisecond;
    }

    public WeekEntryDTO(WeekEntry entity) {
        this.id = entity.getId();
        this.day = entity.getDay();
        this.startMillisecond = entity.getStartMillisecond();
        this.endMillisecond = entity.getEndMillisecond();
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

    public void setStartMillisecond(Long startMillisecond) {
        this.startMillisecond = startMillisecond;
    }

    public Long getEndMillisecond() {
        return endMillisecond;
    }

    public void setEndMillisecond(Long endMillisecond) {
        this.endMillisecond = endMillisecond;
    }

    public Long getClasseId() {
        return classeId;
    }

    public void setClasseId(Long classeId) {
        this.classeId = classeId;
    }

    public WeekEntry toEntity() {
        return new WeekEntry(
                this.id,
                this.day,
                this.startMillisecond,
                this.endMillisecond,
                new Classe(classeId, null, null, null, false, null));
    }
}
