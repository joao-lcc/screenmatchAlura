package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

//      temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
        List<DadosEpisodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()).collect(Collectors.toList());

        //pegando top 5 episódios
        System.out.println("Top 5 episódios");
        episodios.stream()
                .filter(e -> !e.avalicao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avalicao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> eps = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        eps.forEach(System.out::println);

        System.out.println("A partir de que ano você deseja ver os episódios?");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        eps.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e-> {
                    System.out.println("Temporada " + e.getNumTemporada()
                    + ", Episódio " + e.getTitulo()
                    + ", Data de lançamento: " + e.getDataLancamento().format(formatador)
                    );
                });
    }


}
