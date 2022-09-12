package academy.devdojo.springboot.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import academy.devdojo.springboot.domain.Anime;
import lombok.extern.log4j.Log4j2;

/*
 * Essa classe é responsável por receber e informar no console, os 
 * testes envolvendo as urls
 */

/*
 * Anotation responsável por informar no console os retornos
 */
@Log4j2
public class SpringClient {
	public static void main(String[] args) {
		
		//É possível usar "methodForEntity", "methodForObject" e o "exchange", sendo o exchange mais complexo
		// mas mais específico para a manipulação desses testes
		
		//Variavel que recebe toda a informação da requisição
		ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/find/{id}", Anime.class, 2);
		log.info(entity);
		
		//Variável que recebe apenas os dados da entidade em questão da requisição
		Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/find/{id}", Anime.class, 2);
		log.info(object);
		
		//Obter as informações de todos os animes em uma lista, de uma vez
		//Necessária passar o endereço, o método e o parametro de referencia que no caso é lista
		ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/find/all",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<>() {});
		log.info(exchange);
		
		//Teste do método POST: criar uma instância e executar o post for object normalmente, mas passando
		// a instância como parâmetro
		Anime kingdom = Anime.builder().name("Kingdom").build();
		Anime animeSaved = new RestTemplate().postForObject("http://localhost:8080/animes",
				kingdom,
				Anime.class);
		log.info("Saved anime: {}", animeSaved);
		
		//Método PUT
		Anime animeToBeUpdated = animeSaved;
		animeToBeUpdated.setName("Samurai Champloo 2");
		ResponseEntity<Void> samuraiChamplooUpdated = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.PUT,
                new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
                Void.class);
        log.info(samuraiChamplooUpdated);
        
        //Método DELETE
        ResponseEntity<Void> samuraiChamplooDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
               null,
                Void.class,
                animeToBeUpdated.getId());
        log.info(samuraiChamplooDeleted);

	}
	
	 private static HttpHeaders createJsonHeader() {
	        HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	        return httpHeaders;
	    }
}
