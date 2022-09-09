package academy.devdojo.springboot.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/*
 * Especificacao da classe de detalhes de excessoes
 * no caso essa se refere a bad request 
 */
@Getter
@SuperBuilder
public class BadRequestExceptionDetails extends ExceptionDetails {

}
