package online.vidacademica.services.services;

import online.vidacademica.services.entities.State;
import online.vidacademica.services.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository repository;

    public List<State> findAll() {
        return repository.findAll();
    }

    public State findById(Long id) {
        Optional<State> obj = repository.findById(id);
        return obj.get();
    }
}
