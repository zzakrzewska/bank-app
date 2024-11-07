package mas.s24365_projekt_koncowy.model.dto.transfer;

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
public class TransferDetails {

    @Schema(name = "amount", example = "500.00")
    @JsonProperty(value = "amount", required = true)
    String amount;

    @Schema(name = "creationDate", example = "2000-01-01")
    @JsonProperty(value = "creationDate", required = true)
    LocalDate creationDate;

    @Schema(name = "state", example = "Processed")
    @JsonProperty(value = "state", required = true)
    String state;
}
