package academy.devdojo.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.devdojo.springboot.domain.Anime;
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
 */
@RequestMapping(path = "anime")

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
	 * EQUIVALENTE A: localhost:8080/anime/list
	 */
	@GetMapping(path = "list")
	public List<Anime> list() {
		log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return List.of(new Anime("DBZ"), new Anime("Berserker"));
	}

}
