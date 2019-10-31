package online.vidacademica.services.services;

import online.vidacademica.services.dto.TimeBoxDTO;
import online.vidacademica.services.dto.TimesDTO;
import online.vidacademica.services.entities.TimeBox;
import online.vidacademica.services.entities.TimeTable;
import online.vidacademica.services.entities.TimeTableEntry;
import online.vidacademica.services.repositories.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TimeTableService {

	@Autowired
	private TimeTableRepository repository;
	
	public TimesDTO times(Long timeTableId, LocalDate localDate) {
	
		TimeTable timeTable = repository.getOne(timeTableId);
		
		Stream<TimeTableEntry> entries = timeTable.getEntries().stream()
				.filter(e -> e.getDay().equals(localDate.getDayOfWeek())).sorted();
				
		TimesDTO times = new TimesDTO(localDate);
		times.addAllTimeBoxes(entries.map(e -> new TimeBoxDTO(toTimeBox(localDate, e))).collect(Collectors.toList()));
		return times;
	}

	public List<TimesDTO> times(Long timeTableId, LocalDate startDate, LocalDate endDate) {

		List<TimesDTO> list = new ArrayList<>();
		
		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1L)) {
			list.add(times(timeTableId, date));
		}
		
		return list;
	}
	
	private TimeBox toTimeBox(LocalDate localDate, TimeTableEntry entry) {
		Instant start = localDate.atStartOfDay().toInstant(ZoneOffset.UTC).plusMillis(entry.getStartMillisecond());
		Instant end = localDate.atStartOfDay().toInstant(ZoneOffset.UTC).plusMillis(entry.getEndMillisecond());
		return new TimeBox(null, start, end);
	}
	
	/*
	@Transactional(readOnly = true)
	public List<TimeBox> generateTimeBoxes(IntervalDTO dto) {
		
		LocalDateTime dt1 = LocalDateTime.now(ZoneId.systemDefault());
		
//		Instant i1 = d1.toEpochSecond(time, offset)
		
		TimeTable timeTable = repository.getOne(dto.getTimeTableId());

		return timeTable.getEntries().stream()
				.filter(e -> e.getStartSecond() >= dto.getStart().getEpochSecond() && e.getEndSecond() <= dto.getEnd().getEpochSecond())
				.map(e -> e.toTimeBox())
				.sorted()
				.collect(Collectors.toList());	
	}*/	
}
