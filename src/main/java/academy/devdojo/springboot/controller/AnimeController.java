package academy.devdojo.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.devdojo.springboot.domain.Anime;
import academy.devdojo.springboot.request.AnimePostRequestBody;
import academy.devdojo.springboot.request.AnimePutRequestBody;
import academy.devdojo.springboot.service.AnimeService;
import academy.devdojo.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/*
 * Essa anotação (rest controller) além de ser um controlador,
 * faz com que todos os métodos da classe AnimeController
 * retornem um JSON, que é o que a gente quer quando se trata
 * de uma API web.
 * 
 * Esse JSON é acessado a partir dos gets e sets das classes,
 * por isso é importante usá-los
 */
@RestController

/*
 * A anotação request a nivel de classe permite que nós criemos
 * um contexto para diferenciar o retorno das funções dessa classe, 
 * das outras classes controladoras.
 * 
 * Geralmente o path é escrito no plural: animes, usuarios, clientes, etc
 */
@RequestMapping(path = "animes")

/*
 * O lombok é uma ferramenta que facilita a escrita de muitos códigos
 * (não foi explicada profundamente)
 * 
 * imprime no console de forma eficiente o que se deseja
 */
@Log4j2

/*
 * Cria o construtor com o auto wired automaticamente graças ao 
 * lombok
 * 
 * Nesse caso o required é para os atributos finais,
 * caso os atributos fossem normais, deve-se usar o 
 * AllArgsConstructor
 */
@RequiredArgsConstructor
public class AnimeController {
	
	//Attributes
	private final DateUtil dateUtil; // MOSTRARÁ QUANDO FOI ACESSADO O SITE 
	
	/*
	 * Como fará parte do construtor definido pelo @RequiredArgsConstructor,
	 * na classe AnimeService, deve ser definida em sua classe para retornar um bin, para
	 * que seja captada pela notation
	 */
	private final AnimeService animeService; // JOGA A RESPONSABILIDADE PARA A CLASSE SERVICE
	
	
// ESTÁ COMENTADO POIS EQUIVALE A ANOTAÇÃO @AllArgsConstructor
// ---------------------------------------------------------------------
//	//Construtor
//	/*
//	 * O auto wired foi usado pois não é recomendável fazer
//	 * dateUtil = new DateUtil(...), já que os parâmetros não foram
//	 * definidos anteriormente, então o auto wired preenche essa data
//	 * e permite que criemos mesmo sem os parâmetros
//	 * 
//	 * Só consegue acessar a classe que possui a anotação COMPONENT
//	 * 
//	 * Não é recomendada ser utilizada em campos, mas sim no construtor
//	 * pois faciltia os testes unitários, e é a melhor convenção
//	 */
//	@Autowired
//	public AnimeController(DateUtil dateUtil) {
//		super();
//		this.dateUtil = dateUtil;
//	}
	
	
	//Methods
	/*
	 * Os mappings permitem que nós escolhamos o métodos web de retorno
	 * de determinada função, e nesse caso é o get, seguido do contexto
	 * que devemos acessar para obtê-lo
	 * 
	 * EQUIVALENTE A: localhost:8080/animes
	 */
	@GetMapping
	public ResponseEntity<List<Anime>> list() {
		log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		
		//Essa entidade serve para retornar informaõçes extras importantes junto com o JSON
		return ResponseEntity.ok(animeService.listAll()); 
	}
	
	/*
	 * Nesse caso necessita-se usar o path, pq essa classe possui 2 métodos http get
	 * então é necessário diferenciá-los um do outro
	 * 
	 * Note a forma como se capta uma variável passada pela url {variavel} e @PathVariable
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<Anime> findById(@PathVariable Long id) {
		
		//Essa entidade serve para retornar informaõçes extras importantes junto com o JSON
		return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id)); 
	}
	
	/*
	 * Post serve para postar no banco de dados os dados que o usuario registrou durante
	 * seu uso do site
	 * 
	 * Não é  recomendável passar a entidade como parâmetro de um request body, por isso
	 * usa-se o anime request (para evitar que a pessoa possa passar o id como parametro
	 * por exemplo)
	 */
	@PostMapping
	public ResponseEntity<Anime> save(@RequestBody AnimePostRequestBody animePostRequestBody) {
		
		// Essa é outra forma de utilizar a entidade para também retornar o status de criado
		// Lembrando que esse status está associado a um número
		return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
	}
	
	/*
	 * Delete serve para deletar do banco de dados o id que o usuario registrou durante
	 * seu uso do site
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Anime> delete(@PathVariable Long id) {
		
		//Excluir anime
		animeService.remove(id);
		
		// Essa é outra forma de utilizar a entidade para também retornar o status de criado
		// Lembrando que esse status está associado a um número
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/*
	 * Put serve para substituir uma instância já existente
	 */
	@PutMapping
	public ResponseEntity<Anime> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
		
		animeService.replace(animePutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
