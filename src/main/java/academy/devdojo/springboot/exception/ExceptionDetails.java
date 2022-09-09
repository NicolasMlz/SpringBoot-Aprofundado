package academy.devdojo.springboot.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;


/*
 * Essa classe eh responsavel por tratar quais informacoes da excessao
 * personalizada sera mostrada na pagina
 * 
 * Ela por si só não é suficiente para avisar os controllers que eles devem
 * retornar apenas esses dados...
 * 
 * Por isso é necessária a criação do pacote HANDLER que avisará a todos os
 * controllers
 */

@Data
// O super builder eh necessario pois será pai de outras classes de excessoes
@SuperBuilder
public class ExceptionDetails {
	
	//Attributes
	protected  String title;
	protected  int status;
	protected  String details;
	protected  String developerMessage;
	protected  LocalDateTime timestamp;
}
