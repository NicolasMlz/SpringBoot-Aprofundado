package academy.devdojo.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import academy.devdojo.springboot.domain.Anime;
import academy.devdojo.springboot.exception.BadRequestException;
import academy.devdojo.springboot.repository.AnimeRepository;
import academy.devdojo.springboot.request.AnimePostRequestBody;
import academy.devdojo.springboot.request.AnimePutRequestBody;

/*
 * Essa classe se refere à lógica de negócio,
 * e existe para que na classe anime controller, não
 * haja uma poluição muito grande e nem uma concentração
 * de informações e responsabilidades
 */

/*
 * Essa notation tem o mesmo papel da COMPONENT, 
 * mas existe apenas para diferenciar as classes que
 * são serviços, e as que são domínios
 */
@Service
// Notation utilizada para o spring fazer a injecao de 
// dependencias do animeRepository
public class AnimeService {
	
	//Attributes
	/*
	 * O auto wired é essencial para criar uma interface como uma instância, pois
	 * preenche os campos devidos, mesmo sem o construtor, e cria uma bean.
	 */
	@Autowired
	private AnimeRepository animeRepository;
	
	//Methods
	/* O list ALL é pouco utilizado por motivos óbvios*/
	public Page<Anime> list(Pageable pageable) {
		return animeRepository.findAll(pageable);
	}
	
	//Obter todos os animes
	public List<Anime> listAll() {
		return animeRepository.findAll();
	}
	
	//Obter anime por nome
	public List<Anime> findByName(String name) {
		return animeRepository.findByName(name);
	}
	
	//Obter anime por id
	public Anime findByIdOrThrowBadRequestException(Long id) {
		return animeRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Anime Not Found"));
	}

	 /* Essa notation eh responsavel por cuidar de que o metodo nao conclua caso 
	 * alguma exception seja lancada.
	 * 
	 * Sem ela, mesmo com a exception, o metodo salvara o anime passado como parametro
	 * 
	 * Ela pode ser usada para outros metodos tambem
	 */
	@Transactional
	/* 
	 * Salvar anime em um banco de dados através dos dados obtidos no postman
	 */
	public Anime save(AnimePostRequestBody animePostRequestBody) {
		
		// Dessa forma, cria-se uma instancia de anime de forma segura para salvar no BD
		return animeRepository.save(Anime.builder().name(animePostRequestBody.getName()).build());
	}
	
	//Excluir anime da lista de animes através do id obtido na url
	public void remove(Long id) {
		
		//Note que o método findById retorna o anime em questão, e por isso foi usado assim
		animeRepository.delete(findByIdOrThrowBadRequestException(id));
	}
	
	//Substitui o anime passado no site
	public void replace(AnimePutRequestBody animePutRequestBody) {
		
		//Verifica se o anime esta presente no banco de dados para ser atualizado
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
		
		//Cria o novo anime
        Anime anime = Anime.builder()
                .id(savedAnime.getId())
                .name(animePutRequestBody.getName())
                .build();
        
		//Salva no lugar do antigo  
		animeRepository.save(anime);
	}
}
