package mas.s24365_projekt_koncowy.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import mas.s24365_projekt_koncowy.model.Account;
import mas.s24365_projekt_koncowy.model.AccountOwner;
import mas.s24365_projekt_koncowy.model.Role;
import mas.s24365_projekt_koncowy.model.Transfer;
import mas.s24365_projekt_koncowy.model.dto.account.AccountDetails;
import mas.s24365_projekt_koncowy.model.dto.account.AccountDetailsOwner;
import mas.s24365_projekt_koncowy.model.dto.account.AccountDetailsTransaction;
import mas.s24365_projekt_koncowy.repository.AccountRepository;
import mas.s24365_projekt_koncowy.utilities.FormattingUtility;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountsService {

    AccountRepository accountRepository;

    public AccountDetails getAccountDetails(String accountNumber) {
        Optional<Account> account = accountRepository.getAccountByAccountNumber(accountNumber);

        if (account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with given IBAN not found");
        }

        Optional<AccountOwner> primaryOwner = account.get().getAccountOwners()
                .stream()
                .filter(owner -> owner.getRole() == Role.PRIMARY)
                .findFirst();

        return AccountDetails.builder()
                .accountNumber(FormattingUtility
                        .formatBankAccount(account.get().getAccountNumber()))
                .balance(account.get().getBalance())
                .ownerName(primaryOwner
                        .map(accountOwner -> accountOwner.getOwner().getSurname() + " " + accountOwner.getOwner().getName())
                        .orElse(""))
                .build();
    }

    public List<AccountDetailsOwner> getAccountOwners(String accountNumber) {
        Optional<Account> account = accountRepository.getAccountByAccountNumber(accountNumber);

        if (account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with given IBAN not found");
        }

        List<AccountDetailsOwner> accountDetailsOwners = new ArrayList<>();

        for (AccountOwner accountOwner : account.get().getAccountOwners()) {
            accountDetailsOwners.add(AccountDetailsOwner.builder()
                    .name(accountOwner.getOwner().getName())
                    .surname(accountOwner.getOwner().getSurname())
                    .type(accountOwner.getRole().name())
                    .pesel(accountOwner.getOwner().getPESEL())
                    .build());
        }

        return accountDetailsOwners;
    }

    public List<AccountDetailsTransaction> getAccountTransactions(String accountNumber) {
        Optional<Account> account = accountRepository.getAccountByAccountNumber(accountNumber);

        if (account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with given IBAN not found");
        }

        List<AccountDetailsTransaction> accountDetailsTransactions = new ArrayList<>();

        for (Transfer transfer : account.get().getAllTransfers()) {
            Optional<AccountOwner> primaryOwner = transfer.getReceiver().getAccountOwners()
                    .stream()
                    .filter(owner -> owner.getRole() == Role.PRIMARY)
                    .findFirst();

            accountDetailsTransactions.add(AccountDetailsTransaction.builder()
                    .amount(FormattingUtility.formatAmount(transfer.getAmount()))
                    .receiverAccountNumber(FormattingUtility.formatBankAccount(transfer.getReceiver().getAccountNumber()))
                    .transactionReference(transfer.getId())
                    .receiverName(primaryOwner
                            .map(accountOwner -> accountOwner.getOwner().getSurname() + " " + accountOwner.getOwner().getName())
                            .orElse(""))
                    .build());
        }

        if (accountDetailsTransactions.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Account has no transfers associated with it");
        }

        return accountDetailsTransactions;
    }
}
