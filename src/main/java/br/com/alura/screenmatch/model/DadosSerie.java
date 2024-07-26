package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignora o que não encontrar
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(String Title,
                         @JsonAlias("totalSeasons") Integer numTemporadas,
                         @JsonAlias("imdbRating") String avaliacao) {
}
