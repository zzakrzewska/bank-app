package mas.s24365_projekt_koncowy.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountDetails {

    @Schema(name = "Account number", example = "PL18 2056 7223 9122 7470 3531 9282")
    @JsonProperty(value = "accountNumber", required = true)
    String accountNumber;

    @Schema(name = "balance", example = "123.45")
    @JsonProperty(value = "balance", required = true)
    double balance;

    @Schema(name = "Owner name", example = "Kowalski Jan")
    @JsonProperty(value = "ownerName", required = true)
    String ownerName;

}
