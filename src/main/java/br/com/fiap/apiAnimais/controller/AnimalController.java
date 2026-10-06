package br.com.fiap.apiAnimais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.apiAnimais.dto.AnimalCreateRequest;
import br.com.fiap.apiAnimais.dto.AnimalMapper;
import br.com.fiap.apiAnimais.dto.AnimalResponse;
import br.com.fiap.apiAnimais.dto.AnimalUpdateRequest;
import br.com.fiap.apiAnimais.model.Animal;
import br.com.fiap.apiAnimais.service.AnimalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/${api.version}/animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @Autowired
    private AnimalMapper animalMapper;

    @PostMapping
    public ResponseEntity<AnimalResponse> create(@Valid @RequestBody AnimalCreateRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(animalMapper.toDto(
                service.createOrUpdate(
                    animalMapper.toModel(dtoRequest)
                )
            ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> findById(@PathVariable Long id) {
        return service
                .findById(id)
                .map(animal -> animalMapper.toDto(animal))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> findAll() {
        return ResponseEntity.ok(
                service.findAll().stream()
                .map(animal -> animalMapper.toDto(animal))
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> update(@PathVariable Long id,
                                @RequestBody AnimalUpdateRequest dtoRequest) {

        if (service.findById(id).isPresent()) {
            Animal animal = animalMapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(animalMapper.toDto(
                            service.createOrUpdate(animal)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
