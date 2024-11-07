package mas.s24365_projekt_koncowy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank
    @Pattern(regexp = "^[0-9]{16}$")
    @Column(unique = true)
    String cardNumber;
    @NotNull
    Double balance;
    @NotNull
    Double fee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    Account owner;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    final Set<Withdrawal> withdrawals = new HashSet<>();

    public void withdraw(Withdrawal withdrawal) {
        if (this.withdrawals.contains(withdrawal)) {
            return;
        }

        if (withdrawal == null) {
            throw new IllegalArgumentException("Withdrawal cannot be null");
        }

        if (withdrawal.getCard() != this) {
            throw new IllegalArgumentException("Withdrawal must be related to the card");
        }

        if (withdrawal.getAmount() > balance) {
            throw new IllegalArgumentException("Withdrawal cannot exceed available amount on the card");
        }

        this.balance -= withdrawal.getAmount();
        withdrawals.add(withdrawal);
        withdrawal.getAtm().withdraw(withdrawal);
    }
}
