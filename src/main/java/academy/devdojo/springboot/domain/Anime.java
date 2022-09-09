package academy.devdojo.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * O domínio serve para representar as entidades no banco de dados
 * e os objetos no java
 */

/*
 * Lombok
 */
@Data
@AllArgsConstructor

/*
 * Anotations que necessarias para representar essa classe no banco de dados 
 * como uma entidade
 */
@Entity
@NoArgsConstructor
@Builder
public class Anime {
	
	//Attributes
	/*
	 * Anotations necessarias para representar no banco de dados, o id dessa classe
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String name;
	
	// TUDO ISSO SERÁ AUTOMATICAMENTE DEFINIDO PELO LOMBOK
//--------------------------------------------------------
//	//Constructors
//	public Anime() {
//	
//	}
//	public Anime(String name) {
//		super();
//		this.setName(name);
//	}
//
//	//Getter and Setters
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}

}
