package academy.devdojo.springboot.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

/*
 * Esse pacote é responsável por cuidar das definições padrão das requisições web
 * No exemplo em questão, definimos que no método de retorno get, será mostrada
 * apenas a primeira página contendo os 5 elementos
 */
@Configuration
public class DevDojoWebMvnConfigurer implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    	
    	//Note que aqui foram mudados apenas os parametros do Pageable, mas poderiam ser outros
    	//-------------------------------------------------------------------------------------
    	
    	//Alterar especificamente os argumentos que envolvem a pagina
        PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
        //Mudar e adicionar de fato as alteraõçes no projeto
        pageHandler.setFallbackPageable(PageRequest.of(0, 5));
        resolvers.add(pageHandler);
    }
}