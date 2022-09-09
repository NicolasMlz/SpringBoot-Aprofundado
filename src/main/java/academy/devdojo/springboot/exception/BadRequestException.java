package academy.devdojo.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * As excessoes personalizadas podem receber anotacoes relacionadas 
 * especificamente aos retornos http, sendo nesse caso o exemplo
 * da bad request
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{	
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}
}
