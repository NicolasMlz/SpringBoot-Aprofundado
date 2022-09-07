package academy.devdojo.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * O domínio serve para representar as entidades no banco de dados
 * e o objetos no java
 */
@Data
@AllArgsConstructor
public class Anime {
	
	//Attributes
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
