package online.vidacademica.services.services;

import online.vidacademica.services.entities.Country;
import online.vidacademica.services.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository repository;

    public List<Country> findAll() {
        return repository.findAll();
    }

    public Country findById(Long id) {
        Optional<Country> obj = repository.findById(id);
        return obj.get();
    }
}
