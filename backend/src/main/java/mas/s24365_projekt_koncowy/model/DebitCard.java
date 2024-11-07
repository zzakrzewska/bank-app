package mas.s24365_projekt_koncowy.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebitCard extends Card {

    @NotNull
    Double debitAmount;

    public CreditCard changeCardType(double monthlyPayment) {
        return CreditCard.builder()
                .cardNumber(this.getCardNumber())
                .fee(this.getFee())
                .balance(this.getBalance())
                .monthlyPayment(monthlyPayment)
                .owner(this.getOwner())
                .build();
    }
}
