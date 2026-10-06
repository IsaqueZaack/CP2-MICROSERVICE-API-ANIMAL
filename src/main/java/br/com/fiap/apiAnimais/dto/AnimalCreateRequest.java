package br.com.fiap.apiAnimais.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnimalCreateRequest {

    @NotNull
    @Size(min = 2, message = "Nome do animal deve ter no mínimo 2 caracteres")
    private String nome;

    @NotBlank(message = "Espécie do animal é obrigatória")
    private String especie;

    private String idade;

    @NotBlank(message = "Habitat do animal é obrigatório")
    private String habitat;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

}
