package mas.s24365_projekt_koncowy.model.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Client {

    @Schema(name = "name", example = "Katarzyna")
    @JsonProperty(value = "name", required = true)
    String name;

    @Schema(name = "surname", example = "Wasilewska")
    @JsonProperty(value = "surname", required = true)
    String surname;

    @Schema(name = "PESEL", example = "00260575225")
    @JsonProperty(value = "pesel", required = true)
    String pesel;
}
