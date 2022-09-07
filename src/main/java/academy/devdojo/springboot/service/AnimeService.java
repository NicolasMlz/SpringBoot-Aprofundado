package academy.devdojo.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import academy.devdojo.springboot.domain.Anime;
import academy.devdojo.springboot.repository.AnimeRepository;

/*
 * Essa notation tem o mesmo papel da COMPONENT, 
 * mas existe apenas para diferenciar as classes que
 * são serviços, e as que são domínios
 */
@Service
/*
 * Essa classe se refere à lógica de negócio,
 * e existe para que na classe anime controller, não
 * haja uma poluição muito grande e nem uma concentração
 * de informações e responsabilidades
 */
public class AnimeService implements AnimeRepository{
	
	//Attributes
	private static List<Anime> animes; 
	//Essa técnica permite a lista ser estática e ter o tamanho variável por ser ArrayList (animes.add)
	static {
		animes = new ArrayList<>(List.of(new Anime(1L, "Boku no Hero"), new Anime(2L, "Berserker")));
	}
	
	//Methods
	/* O list ALL é pouco utilizado por motivos óbvios*/
	public List<Anime> listAll() {
		return animes;
	}
	
	//Obter anime por id
	public Anime findById(Long id) {
		
		/*
		 * Precisa usar stream pois estamos acessando a lista completa de animes 
		 * e há como utilizar o BAD REQUEST como resultado caso não encontre nada
		 */
		return animes.stream()
				.filter(e -> e.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found."));
	}

	//Salvar anime em um banco de dados através dos dados obtidos no postman
	public Anime save(Anime anime) {
		
		// Definir id aleatório para o usuário não precisar digitar
		anime.setId(ThreadLocalRandom.current().nextLong(3, 100000));
		animes.add(anime); // Salvar novo anime na lista
		return anime;
	}
}
