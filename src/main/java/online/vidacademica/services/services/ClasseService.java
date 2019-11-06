package online.vidacademica.services.services;

import online.vidacademica.services.dto.ClasseDTO;
import online.vidacademica.services.dto.SubjectDTO;
import online.vidacademica.services.dto.TimeBoxDTO;
import online.vidacademica.services.dto.TimesDTO;
import online.vidacademica.services.entities.Classe;
import online.vidacademica.services.entities.Subject;
import online.vidacademica.services.entities.WeekEntry;
import online.vidacademica.services.repositories.ClassRepository;
import online.vidacademica.services.repositories.SubjectRepository;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ClasseService {

    @Autowired
    private ClassRepository repository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Classe> findAll() {
        return repository.findAll();
    }


    public ClasseDTO findById(Long id) {
        Optional<Classe> obj = repository.findById(id);
        Classe entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
        return new ClasseDTO(entity);
    }

    public ClasseDTO insert(ClasseDTO dto) {
        Classe entity = dto.toEntity();
        entity = repository.save(entity);
        return new ClasseDTO(entity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Classe não pode ser excluido!");
        }
    }

    public ClasseDTO update(Long id, ClasseDTO dto) {
        try {
            Classe entity = repository.getOne(id);
            updateData(entity, dto);
            entity = repository.save(entity);
            return new ClasseDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Classe entity, ClasseDTO dto) {
        entity.setName(dto.getName());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setActive(dto.isActive());
        entity.setCreationDate(dto.getCreationDate());
    }

    @Transactional
    public void setSubject(Long id, SubjectDTO dto) {
        Subject subject = subjectRepository.getOne(dto.getId());
        Classe classe = repository.getOne(id);
        classe.setSubject(subject);
        repository.save(classe);
    }

    @Transactional(readOnly = true)
    public List<ClasseDTO> findBySubjectId(Long subjectId) {
        Subject subject = subjectRepository.getOne(subjectId);
        List<Classe> list = repository.findBySubject(subject);

        return list.stream().map(e -> new ClasseDTO(e)).collect(Collectors.toList());
    }

    private TimesDTO times(Long timeTableId, LocalDate localDate) {

        Classe classe = repository.getOne(timeTableId);

        if (localDate.isBefore(classe.getStartDate()) || localDate.isAfter(classe.getEndDate())) return null;

        Stream<WeekEntry> entries = classe.getWeekEntries().stream()
                .filter(e -> e.getDay().equals(localDate.getDayOfWeek())).sorted();

        TimesDTO times = new TimesDTO(localDate);
        times.addAllTimeBoxes(entries.map(e -> toTimeBoxDto(localDate, e)).collect(Collectors.toList()));
        return times;
    }

    public List<TimesDTO> times(Long timeTableId, LocalDate startDate, LocalDate endDate) {

        List<TimesDTO> list = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1L)) {
            TimesDTO timeDTO = times(timeTableId, date);
            if (timeDTO != null) {
                list.add(timeDTO);
            }
        }
        return list;
    }

    private TimeBoxDTO toTimeBoxDto(LocalDate localDate, WeekEntry entry) {
        Instant start = localDate.atStartOfDay().toInstant(ZoneOffset.UTC).plusMillis(entry.getStartMillisecond());
        Instant end = localDate.atStartOfDay().toInstant(ZoneOffset.UTC).plusMillis(entry.getEndMillisecond());
        return new TimeBoxDTO(start, end);
    }
}
