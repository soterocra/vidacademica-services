package online.vidacademica.services.entities.interfaces;


import java.io.Serializable;
import java.time.DayOfWeek;

public interface TimeEntry extends Serializable, Comparable<TimeEntry> {

	DayOfWeek getDay();
	Long getStartMillisecond();
	Long getEndMillisecond();


}
