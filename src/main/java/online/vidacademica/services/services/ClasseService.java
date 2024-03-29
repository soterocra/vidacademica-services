package online.vidacademica.services.services;

import online.vidacademica.services.dto.*;
import online.vidacademica.services.entities.*;
import online.vidacademica.services.repositories.*;
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

import static online.vidacademica.services.util.Json.toJson;

@Service
public class ClasseService {

    @Autowired
    private ClassRepository repository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private WeekEntryRepository timeTableEntryRepository;

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

    @Transactional
    public void atachStudent(RegistrationDTO dto) {
        User user = userRepository.findByName(dto.getUser());
        Classe classe = classRepository.findByName(dto.getClasse());

        Registration entity = new Registration(null, Instant.now(), user, classe);
        registrationRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findUsersByClassId(Long classeId) {
        Classe classe = repository.getOne(classeId);
        List<Registration> list = registrationRepository.findRegistrationByClasse(classe);
        List<User> users = new ArrayList<>();

        Long [] arrayIds = new Long[list.size()];
        int x = 0;
        for (Registration registration: list) {
            arrayIds[x++] = registration.getUser().getId();
        }
        Long []students = arrayIds;

        for (int i = 0; i <arrayIds.length ; i++) {
            User user = userRepository.getOne(arrayIds[i]);
            users.add(user);
        }
        
        
        return users.stream().map(e -> new UserDTO(e)).collect(Collectors.toList());

    }
    @Transactional(readOnly = true)
    public List<ClasseDTO> findByRegistrationId(Long registrationId) {
        Registration registration = registrationRepository.getOne(registrationId);
        List<Classe> list = repository.findByRegistration(registration);

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

    @Transactional
    public WeekEntryDTO addWeekEntry(WeekEntryDTO dto) {
        WeekEntry entity = dto.toEntity();
        entity.setClasse(classRepository.findById(dto.getClasseId()).orElseThrow(() -> new ResourceNotFoundException(dto.getClasseId())));

        Long conflicts = timeTableEntryRepository.findByClasse(entity.getClasse()).stream().filter(weekEntry -> weekEntry.conflicts(entity)).count();

        if (conflicts > 0) {
            throw new DatabaseException("Já existe programação para esse horário da semana.");
        }

        return new WeekEntryDTO(timeTableEntryRepository.save(entity));
    }
}
