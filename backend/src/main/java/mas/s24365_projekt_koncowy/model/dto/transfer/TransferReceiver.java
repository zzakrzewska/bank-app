package mas.s24365_projekt_koncowy.model.dto.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransferReceiver {

    @Schema(name = "name", example = "Wasilewska Katarzyna")
    @JsonProperty(value = "name", required = true)
    String name;

    @Schema(name = "accountNumber", example = "PL18 2056 7223 9122 7470 3531 9282")
    @JsonProperty(value = "accountNumber", required = true)
    String accountNumber;

    @Schema(name = "pesel", example = "02248969192")
    @JsonProperty(value = "pesel", required = true)
    String pesel;
}
