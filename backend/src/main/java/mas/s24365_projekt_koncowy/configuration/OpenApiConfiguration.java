package mas.s24365_projekt_koncowy.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bank API")
                        .description("Projekt koncowy na przedmiot Modelowanie i Analiza Systemow Informatycznych")
                        .contact(new Contact()
                                .name("Zofia Zakrzewska")
                                .email("s24365@pjwstk.edu.pl"))
                        .version("1.0.0"));
    }

}