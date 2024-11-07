package mas.s24365_projekt_koncowy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ATM {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Address", nullable = false)
    Address address;

    @NotNull
    Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    Bank owner;

    @OneToMany(mappedBy = "atm", fetch = FetchType.LAZY)
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

        if (withdrawal.getAtm() != this) {
            throw new IllegalArgumentException("Withdrawal must be related to the ATM");
        }

        if (withdrawal.getAmount() > amount) {
            throw new IllegalArgumentException("Withdrawal cannot exceed available amount in the ATM");
        }

        this.amount -= withdrawal.getAmount();
        withdrawals.add(withdrawal);
        withdrawal.getCard().withdraw(withdrawal);
    }
}
