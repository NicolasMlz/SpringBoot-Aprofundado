package academy.devdojo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ESSAS 3 ANOTAÇÕES SÃO ESSENCIAIS E ESTÃO COMENTADAS POIS SE ENCONTRAM DENTRO DA ANOTAÇÃO SPRINGBOOTAPLICATION
// -----------------------------------------------------------------------------
///*
// * Essa classe é a main, que irá iniciar o projeto em uma porta
// * através do tomcat e outras dependências...
// * 
// * Sempre será inicializado dessa forma.
// */
//@EnableAutoConfiguration
//
///*
// * Se a classe de inicialização estivesse em um pacote diferente 
// * do padrão, seria necessário que usassemos o component scan para mudar 
// * a convenção original do spring
// */
//@ComponentScan(basePackages = "academy.devdojo.springboot")
//
///*
// * Tansforma a classe em bin possivel de ser scaneado pelo Spring
// * 
// * E nesse caso será utilizada para configurar a segurança da
// * aplicação
// */
//@Configuration

@SpringBootApplication
public class ApplicationStart {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}
}
