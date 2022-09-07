package academy.devdojo.springboot.repository;

import java.util.List;
import academy.devdojo.springboot.domain.Anime;

/*
 * Essa classe será utilizada para fazer a conexão entre 
 * o banco de dados e a API
 */
public interface AnimeRepository {
	
	// Attributes
	List<Anime> listAll();

}
