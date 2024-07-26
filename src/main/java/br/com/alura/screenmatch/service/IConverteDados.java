package br.com.alura.screenmatch.service;

public interface IConverteDados {
    //Método genérico, não sabemos o que iremos enviar, por isso especificamos
    //a classe no segundo parâmetro.
    <T> T obterDados(String json, Class<T> classe);
}
