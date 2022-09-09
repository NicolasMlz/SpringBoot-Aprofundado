package academy.devdojo.springboot.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AnimePostRequestBody {
	
	//Attributes
	/*
	 * A validação nesse campo será relacionada ao fato dele não
	 * poder ser nulo, mas há outros tipos de validações como
	 * o @Url, @NotEmpty, etc, responsáveis por tratar de forma
	 * eficiente o parâmetro que será armazenado no banco de dados
	 */
	@NotEmpty(message = "Anime name cannot be null.")
	private String name;
}
