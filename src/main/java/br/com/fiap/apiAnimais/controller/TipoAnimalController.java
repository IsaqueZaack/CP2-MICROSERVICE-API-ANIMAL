package br.com.fiap.apiAnimais.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.fiap.apiAnimais.model.TipoAnimal;
import br.com.fiap.apiAnimais.repository.TipoAnimalRepository;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/${api.version}/tipoAnimal")
public class TipoAnimalController {
    @Autowired
    private TipoAnimalRepository repository;

    @PostMapping()
    public ResponseEntity<TipoAnimal> create(@RequestBody TipoAnimal tipoAnimal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tipoAnimal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAnimal> findById(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TipoAnimal>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoAnimal> update(@PathVariable Long id,
            @RequestBody TipoAnimal tipoAnimal) {

        Optional<TipoAnimal> optTipoAnimal = repository.findById(id);

        if (optTipoAnimal.isPresent()) {
            tipoAnimal.setId(id);
            TipoAnimal tipoAnimalAlterado = repository.save(tipoAnimal);
            return ResponseEntity.ok(tipoAnimalAlterado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
