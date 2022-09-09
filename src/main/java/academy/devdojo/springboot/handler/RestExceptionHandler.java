package academy.devdojo.springboot.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import academy.devdojo.springboot.exception.BadRequestException;
import academy.devdojo.springboot.exception.BadRequestExceptionDetails;
import academy.devdojo.springboot.exception.ValidationExceptionDetails;

/*
 * A classe rest exception pode abrigar diversas exceptions, nesse exemplo foi tratada apenas 
 * a bad request, especificando apenas alguns detalhes do erro
 * 
 *  A anotacao controller advice literalmente avisa os controladores sobre como a exception deve ser
 *  tratada e mostrada ao usuario

 */
@ControllerAdvice
public class RestExceptionHandler {
	
	//Especificamente sera trabalhada a bad request exception
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
		return new ResponseEntity<> (
				BadRequestExceptionDetails.builder()
					.title("Bad Request Exception, check the documentation")
					.status(HttpStatus.BAD_REQUEST.value())
					.details(bre.getMessage())
					.developerMessage(bre.getClass().getName())
					.timestamp(LocalDateTime.now())
					.build(), HttpStatus.BAD_REQUEST);
	}
	
	//Especificacao da excessao que se refere à validãaço dos dados
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
			MethodArgumentNotValidException ve) {
		
		//Acessar todos os erros que podem ter sido cometidos
		List<FieldError> fieldErrors = ve.getBindingResult().getFieldErrors();
		
		//Acessar os titulos dos erros
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining("; "));
		
		//Acessar as descricoes dos erros
		String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("; "));
		
		return new ResponseEntity<ValidationExceptionDetails>(
				ValidationExceptionDetails.builder()
				.title("Bad Request Exception, invalid field(s).")
				.status(HttpStatus.BAD_REQUEST.value())
				.details("CHheck the fields error.")
				.developerMessage(ve.getClass().getName())
				.timestamp(LocalDateTime.now())
				.fields(fields)
				.fieldsMessage(fieldsMessage)
				.build(), HttpStatus.BAD_REQUEST);
	}
}
