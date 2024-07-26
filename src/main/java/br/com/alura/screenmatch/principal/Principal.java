package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final String URL = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=1aeeb968";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados cd = new ConverteDados();

    public void Menu() {

        System.out.println("Digite o nome da série para busca: ");
        var nome = leitura.nextLine().replace(" ", "+");
        var endereco = URL + nome + API_KEY;
        var json = consumoAPI.obterDados(endereco);
        DadosSerie dadosSerie = cd.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);
		List<DadosTemporada> temporadas = new ArrayList<>();
		for(int i = 1; i <= dadosSerie.numTemporadas(); i++)
		{
			endereco = URL + nome + "&season=" + i + API_KEY;
			json = consumoAPI.obterDados(endereco);
			DadosTemporada dadosTp = cd.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTp);
		}
		temporadas.forEach(System.out::println);

//        for (int i = 0; i < dadosSerie.numTemporadas(); i++) {
//            List<DadosEpisodio> episodios = temporadas.get(i).episodios();
//            for (int j = 0; j < episodios.size(); j++){
//                System.out.println(episodios.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }

}
