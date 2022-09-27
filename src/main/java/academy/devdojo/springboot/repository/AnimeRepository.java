package academy.devdojo.springboot.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import academy.devdojo.springboot.domain.Anime;

/*
 * Essa classe será utilizada para fazer a conexão entre 
 * o banco de dados e a API
 */

/*
 * A anotação primary é essencial para que o erro "Required a bean of AnimeRepository"
 * não ocorra.
 * 
 * Como essa interface é um JpaRepository, é criado uma bean automaticamente devido a algum
 * mecanismo dentro do JpaRepository, então quando eu instâncio essa interface no service, 
 * dará erro pois há mais de uma bean (dentor do Jpa e dentro do service).
 * 
 * Por isso essa notation é válida, pois indica que apenas o local que eu usar o auto wired
 * é que será intanciado uma bean
 */
@Primary
public interface AnimeRepository extends JpaRepository<Anime, Long>{
	
	/* Como essa classe parte da clase spring chamada de jparepository,
	 * ja possui diversos metodos SQL pre-estabelecidos como findById, 
	 * entre outros...
	 */
	
	/*
	 * Eh possivel estabelecer pesquisas sql especificas
	 */
	public List<Anime> findByName(String name);

	
}
