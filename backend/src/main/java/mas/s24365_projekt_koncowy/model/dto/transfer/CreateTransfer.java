package mas.s24365_projekt_koncowy.model.dto.transfer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateTransfer {

    @Pattern(regexp = "^(PL)[0-9]{26}$")
    @Schema(description = "Account number in IBAN format", example = "PL60394020300000394003494444")
    String senderAccountNumber;

    @Pattern(regexp = "^(PL)[0-9]{26}$")
    @Schema(description = "Account number in IBAN format", example = "PL60394020300000394003494444")
    String receiverAccountNumber;

    @NotNull
    @DecimalMin(value = "0.01")
    BigDecimal amount;
}
