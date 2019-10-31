package online.vidacademica.services.resources;

import online.vidacademica.services.dto.TimesDTO;
import online.vidacademica.services.services.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/timetable")
public class TimeTableResource {

	@Autowired
	private TimeTableService service;
	
	@GetMapping(value = "/{timeTableId}/timeboxes")
	public ResponseEntity<TimesDTO> timeboxes(@RequestParam(value="date") String date, @PathVariable Long timeTableId) {
		LocalDate d = LocalDate.parse(date);
		TimesDTO times = service.times(timeTableId, d);
		return ResponseEntity.ok().body(times);
	}

	@GetMapping(value = "/{timeTableId}/timeboxesrange")
	public ResponseEntity<List<TimesDTO>> timeboxesrange(
			@RequestParam(value="startDate") String startDate, 
			@RequestParam(value="endDate") String endDate, 
			@PathVariable Long timeTableId) {
		LocalDate d1 = LocalDate.parse(startDate);
		LocalDate d2 = LocalDate.parse(endDate);
		List<TimesDTO> list = service.times(timeTableId, d1, d2);
		return ResponseEntity.ok().body(list);
	}
}
