package br.com.fiap.apiAnimais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.apiAnimais.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
