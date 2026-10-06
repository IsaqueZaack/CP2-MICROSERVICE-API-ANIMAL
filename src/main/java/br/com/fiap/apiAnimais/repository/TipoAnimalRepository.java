package br.com.fiap.apiAnimais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fiap.apiAnimais.model.TipoAnimal;

@Repository
public interface TipoAnimalRepository extends JpaRepository<TipoAnimal, Long> {

}
