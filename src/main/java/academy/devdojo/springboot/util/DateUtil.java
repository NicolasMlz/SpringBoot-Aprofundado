package academy.devdojo.springboot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/*
 * Tansforma a classe em bin possivel de ser scaneado pelo Spring
 */
@Component
public class DateUtil {
	
	// Metodo que formata a data para um determinado padrão
	public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
	}
}
