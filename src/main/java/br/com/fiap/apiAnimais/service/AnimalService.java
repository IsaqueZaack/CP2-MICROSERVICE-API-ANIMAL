package br.com.fiap.apiAnimais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.apiAnimais.model.Animal;
import br.com.fiap.apiAnimais.repository.AnimalRepository;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository repository;

    public Animal createOrUpdate(Animal animal) {
        return repository.save(animal);
    }

    public Optional<Animal> findById(Long id) {
        return repository
                .findById(id);
    }

    public List<Animal> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
