package academy.devdojo.springboot.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/*
 * Especificacao da classe de detalhes de excessoes
 * no caso essa se refere as validacoes dos dados
 */
@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails{
	
	//Attributes
	private final String fields;
	private final String fieldsMessage;
}
