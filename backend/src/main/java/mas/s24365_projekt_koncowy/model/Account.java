package mas.s24365_projekt_koncowy.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank
    @Pattern(regexp = "^(PL)[0-9]{26}$")
    @Column(unique = true)
    @Schema(description = "Account number in IBAN format", example = "PL60394020300000394003494444")
    String accountNumber;
    @NotNull
    Double balance;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    final List<Transfer> receivedTransfers = new ArrayList<>();
    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    final List<Transfer> sentTransfers = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    final Set<Card> cards = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    Bank bank;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @NotEmpty
    final Set<AccountOwner> accountOwners = new HashSet<>();

    public void addAccountOwner(AccountOwner accountOwner) {
        if (accountOwners.contains(accountOwner)) {
            return;
        }

        if (accountOwner == null) {
            throw new IllegalArgumentException("An account owner cannot be null");
        }

        if (!accountOwner.getOwner().getTypes().contains(Type.CLIENT)) {
            throw new IllegalArgumentException("An account owner must be a client");
        }

        if (accountOwner.getAccount() != this) {
            throw new IllegalArgumentException("An account owner must be connected to the account");
        }

        accountOwners.add(accountOwner);
        accountOwner.getOwner().addAccountOwner(accountOwner);
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("A card cannot be null");
        }

        if (card.getOwner() != this) {
            throw new IllegalArgumentException("A card must be associated with the account");
        }

        double balanceSum = 0;
        for (Card c : cards) {
            balanceSum += c.getBalance();
        }

        if (balanceSum > this.balance) {
            throw new IllegalArgumentException("Cards balance cannot go over account's total balance");
        }

        cards.add(card);
    }

    public void receiveTransfer(Transfer transfer) {
        if (receivedTransfers.contains(transfer)) {
            return;
        }

        if (transfer == null) {
            throw new IllegalArgumentException("Transfer cannot be null");
        }

        if (transfer.getReceiver() != this) {
            throw new IllegalArgumentException("Transfer's receiver must be this account");
        }

        this.balance += transfer.getAmount();
        receivedTransfers.add(transfer);

        if(transfer.getSender() != null) {
            transfer.getSender().sendTransfer(transfer);
        }
    }

    public void sendTransfer(Transfer transfer) {
        if (sentTransfers.contains(transfer)) {
            return;
        }

        if (transfer == null) {
            throw new IllegalArgumentException("Transfer cannot be null");
        }

        if (transfer.getSender() != this) {
            throw new IllegalArgumentException("Transfer's sender must be this account");
        }

        if (this.balance - transfer.getAmount() < 0) {
            throw new IllegalArgumentException("Transfer's amount cannot exceed the available balance");
        }

        this.balance -= transfer.getAmount();
        sentTransfers.add(transfer);

        if(transfer.getReceiver() != null) {
            transfer.getReceiver().receiveTransfer(transfer);
        }
    }

    public List<Transfer> getSentTransfers() {
        return sentTransfers
                .stream()
                .sorted(Comparator.comparing(Transfer::getTransferDate))
                .collect(Collectors.toList());
    }

    public List<Transfer> getReceivedTransfers() {
        return receivedTransfers
                .stream()
                .sorted(Comparator.comparing(Transfer::getTransferDate))
                .collect(Collectors.toList());
    }

    public List<Transfer> getAllTransfers() {
        List<Transfer> allTransfers = Stream.concat(receivedTransfers.stream(), sentTransfers.stream()).toList();

        return allTransfers
                .stream()
                .sorted(Comparator.comparing(Transfer::getTransferDate))
                .collect(Collectors.toList());
    }
}
