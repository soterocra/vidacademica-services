package online.vidacademica.services.services;

import online.vidacademica.services.dto.ClasseDTO;
import online.vidacademica.services.dto.SubjectDTO;
import online.vidacademica.services.dto.UserDTO;
import online.vidacademica.services.entities.*;
import online.vidacademica.services.repositories.*;
import online.vidacademica.services.resources.exceptions.DatabaseException;
import online.vidacademica.services.services.exceptions.AddUserToSubjectException;
import online.vidacademica.services.services.exceptions.JWTAuthenticationException;
import online.vidacademica.services.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository repository;


    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private AuthService authservice;


}

