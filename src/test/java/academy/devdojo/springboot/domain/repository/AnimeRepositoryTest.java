package academy.devdojo.springboot.domain.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import academy.devdojo.springboot.domain.Anime;
import academy.devdojo.springboot.repository.AnimeRepository;


/*
 * Essa classe é responsável por realizar os testes do anime repository,
 * ou seja, o que está relacionado ao banco de dados...
 * 
 * É importante destacar que essa classe de testes utiliza um bd provisório
 * que será resetado (row back transactions) a cada vez que essa classe for
 * executado.
 * 
 * e que apesar de não parecer, os testes unitários são muito vistos pelas
 * empresas
 */
@DataJpaTest //Especificar que é um teste de banco de dados
@DisplayName("Tests for anime repository")
class AnimeRepositoryTest {
	
	// Criar instância de um anime repository
	@Autowired
	private AnimeRepository animeRepository;
	
	/*
	 * Esse método é nomeado começando pelo nome do método na classe da api,
	 * seguido do que ele faz e finalmente quando ele deve realizar tal ação
	 */
	@Test
	@DisplayName("Save creates anime when succesful")
	void save_PersistAnime_WhenSuccesful() {
		
		//Criar anime
		Anime animeToBeSaved = createAnime();
		
		//Salvar anime no banco de dados
		Anime animeSaved = animeRepository.save(animeToBeSaved);
		
		//Realizar os testes de verificação
		
		Assertions.assertThat(animeSaved).isNotNull(); //Verificar se há anime
		Assertions.assertThat(animeSaved.getId()).isNotNull();//Veriicar se há ID
		//Verificar se o nome do anime salvo é o mesmo nome do anime criado na api
		Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName()); 
		
	}

	// Método responsável por criar um anime qualquer
	private Anime createAnime() {
		return Anime.builder().name("Akira").build();
	}
}
