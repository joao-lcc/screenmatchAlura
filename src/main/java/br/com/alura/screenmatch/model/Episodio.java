package br.com.alura.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private String titulo;
    private Integer numEpisodio;
    private Integer numTemporada;
    private Double avalicao;
    private LocalDate dataLancamento;

    public Episodio(Integer numTemporada, DadosEpisodio d) {
        this.numTemporada = numTemporada;
        this.titulo = d.titulo();
        this.numEpisodio = d.numEpisodio();
        try {
            this.avalicao = Double.valueOf(d.avalicao());
        }catch(NumberFormatException ex) {
            this.avalicao = 0.0;
        }
        try {
            this.dataLancamento = LocalDate.parse(d.dataLancamento());
        }catch(DateTimeParseException ex) {
            this.dataLancamento = null;
        }
    }

    @Override
    public String toString() {
        return "(titulo = '" + titulo +
                ", numEpisodio = " + numEpisodio +
                ", numTemporada = " + numTemporada +
                ", avalicao = " + avalicao +
                ", dataLancamento = " + dataLancamento +
                ')';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumEpisodio() {
        return numEpisodio;
    }

    public void setNumEpisodio(Integer numEpisodio) {
        this.numEpisodio = numEpisodio;
    }

    public Integer getNumTemporada() {
        return numTemporada;
    }

    public void setNumTemporada(Integer numTemporada) {
        this.numTemporada = numTemporada;
    }

    public Double getAvalicao() {
        return avalicao;
    }

    public void setAvalicao(Double avalicao) {
        this.avalicao = avalicao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
