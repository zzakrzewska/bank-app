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
public class AccountDetailsTransaction {

    @Schema(name = "accountNumber", example = "PL18 2056 7223 9122 7470 3531 9282")
    @JsonProperty(value = "accountNumber", required = true)
    String receiverAccountNumber;

    @Schema(name = "name", example = "Wasilewska Katarzyna")
    @JsonProperty(value = "name", required = true)
    String receiverName;

    @Schema(name = "amount", example = "500.50")
    @JsonProperty(value = "amount", required = true)
    String amount;

    @Schema(name = "transaction reference id", example = "184832")
    @JsonProperty(value = "transactionReference", required = true)
    long transactionReference;
}
