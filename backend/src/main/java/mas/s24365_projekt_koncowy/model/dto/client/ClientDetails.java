package mas.s24365_projekt_koncowy.model.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientDetails {

    @Schema(name = "name", example = "Katarzyna")
    @JsonProperty(value = "name", required = true)
    String name;

    @Schema(name = "surname", example = "Wasilewska")
    @JsonProperty(value = "surname", required = true)
    String surname;

    @Schema(name = "PESEL", example = "00260575225")
    @JsonProperty(value = "pesel", required = true)
    String pesel;

    @Schema(name = "birthDate", example = "2000-01-01")
    @JsonProperty(value = "birthDate", required = true)
    LocalDate birthDate;

    @Schema(name = "age", example = "25")
    @JsonProperty(value = "age", required = true)
    Integer age;

    @Schema(name = "address", example = "02-225 Warszawa, Morelowa 64A/20")
    @JsonProperty(value = "address", required = true)
    String address;

    @Schema(name = "telephoneNumber", example = "600 111 222")
    @JsonProperty(value = "telephoneNumber", required = true)
    String telephoneNumber;

    @Schema(name = "mail", example = "dadamska@gmail.com")
    @JsonProperty(value = "mail")
    String mail;

}
