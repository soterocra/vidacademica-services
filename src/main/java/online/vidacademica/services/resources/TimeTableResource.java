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
    public ResponseEntity<List<TimesDTO>> timeboxes(
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @PathVariable Long timeTableId) {
        if (endDate == null) {
            endDate = startDate;
        }
        LocalDate d1 = LocalDate.parse(startDate);
        LocalDate d2 = LocalDate.parse(endDate);
        List<TimesDTO> list = service.times(timeTableId, d1, d2);
        return ResponseEntity.ok().body(list);
    }
}
