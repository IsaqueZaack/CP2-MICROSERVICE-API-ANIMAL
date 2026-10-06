package br.com.fiap.apiAnimais.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.fiap.apiAnimais.model.Animal;

@Component
public class AnimalMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Animal toModel(AnimalCreateRequest dto) {
        return modelMapper.map(dto, Animal.class);
    }

    public AnimalResponse toDto(Animal entity) {
        return modelMapper.map(entity, AnimalResponse.class);
    }

    public Animal toModel(Long id, AnimalUpdateRequest dto) {
        Animal animal = modelMapper.map(dto, Animal.class);
        animal.setId(id);
        return animal;
    }
}
