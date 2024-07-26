package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		String end = "http://www.omdbapi.com/?t=gilmore+girls&apikey=1aeeb968";
		var json = consumoAPI.obterDados(end);
		System.out.println(json);
		ConverteDados cd = new ConverteDados();
		DadosSerie dados = cd.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		end = "http://www.omdbapi.com/?t=gilmore+girls&season=1&episode=1&apikey=1aeeb968";
		json = consumoAPI.obterDados(end);
		DadosEpisodio dadosEp = cd.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEp);

	}

}
