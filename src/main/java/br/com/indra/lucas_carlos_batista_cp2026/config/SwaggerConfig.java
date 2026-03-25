package br.com.indra.lucas_carlos_batista_cp2026.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - JP2026")
                        .version("1.0.0")
                        .description("Documentação da API de Estoque, Produtos, Carrinho e Categorias")
                        .contact(new Contact()
                                .name("Lucas Carlos Batista")
                                .email("lucassscarlosss54@gmail.com"))
                );
    }
}
