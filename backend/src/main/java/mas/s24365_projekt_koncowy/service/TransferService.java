package mas.s24365_projekt_koncowy.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import mas.s24365_projekt_koncowy.model.AccountOwner;
import mas.s24365_projekt_koncowy.model.Role;
import mas.s24365_projekt_koncowy.model.State;
import mas.s24365_projekt_koncowy.model.Transfer;
import mas.s24365_projekt_koncowy.model.dto.transfer.CreateTransfer;
import mas.s24365_projekt_koncowy.model.dto.transfer.TransferDetails;
import mas.s24365_projekt_koncowy.model.dto.transfer.TransferReceiver;
import mas.s24365_projekt_koncowy.model.dto.transfer.TransferSender;
import mas.s24365_projekt_koncowy.repository.AccountRepository;
import mas.s24365_projekt_koncowy.repository.TransferRepository;
import mas.s24365_projekt_koncowy.utilities.FormattingUtility;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransferService {

    TransferRepository transferRepository;
    AccountRepository accountRepository;

    public TransferDetails getTransactionDetails(String transactionId) {
        Optional<Transfer> transfer = transferRepository.getTransferById(Long.parseLong(transactionId));

        if (transfer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer with given ID not found");
        }

        return TransferDetails.builder()
                .amount(FormattingUtility.formatAmount(transfer.get().getAmount()))
                .creationDate(transfer.get().getTransferDate())
                .state(transfer.get().getState().name())
                .build();
    }

    public TransferReceiver getTransactionReceiver(String transactionId) {
        Optional<Transfer> transfer = transferRepository.getTransferById(Long.parseLong(transactionId));

        if (transfer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer with given ID not found");
        }

        Optional<AccountOwner> primaryOwner = transfer.get().getReceiver().getAccountOwners()
                .stream()
                .filter(owner -> owner.getRole() == Role.PRIMARY)
                .findFirst();

        return TransferReceiver.builder()
                .accountNumber(FormattingUtility
                        .formatBankAccount(transfer.get().getReceiver().getAccountNumber()))
                .name(primaryOwner
                        .map(accountOwner -> accountOwner.getOwner().getSurname() + " " + accountOwner.getOwner().getName())
                        .orElse(""))
                .pesel(primaryOwner
                        .map(accountOwner -> accountOwner.getOwner().getPESEL())
                        .orElse(""))
                .build();
    }

    public TransferSender getTransactionSender(String transactionId) {
        Optional<Transfer> transfer = transferRepository.getTransferById(Long.parseLong(transactionId));

        if (transfer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer with given ID not found");
        }

        Optional<AccountOwner> primaryOwner = transfer.get().getSender().getAccountOwners()
                .stream()
                .filter(owner -> owner.getRole() == Role.PRIMARY)
                .findFirst();

        return TransferSender.builder()
                .accountNumber(FormattingUtility
                        .formatBankAccount(transfer.get().getSender().getAccountNumber()))
                .name(primaryOwner
                        .map(accountOwner -> accountOwner.getOwner().getSurname() + " " + accountOwner.getOwner().getName())
                        .orElse(""))
                .pesel(primaryOwner
                        .map(accountOwner -> accountOwner.getOwner().getPESEL())
                        .orElse(""))
                .build();
    }

    public Long createTransfer(CreateTransfer createTransfer) {
        if (createTransfer.getSenderAccountNumber().equals(createTransfer.getReceiverAccountNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender and receiver cannot be the same");
        }

        Transfer transfer = Transfer.builder()
                .amount(createTransfer.getAmount().doubleValue())
                .state(State.CREATED)
                .transferDate(LocalDate.now())
                .build();

        accountRepository.getAccountByAccountNumber(createTransfer.getSenderAccountNumber())
                .ifPresentOrElse(account -> {
                            transfer.setSender(account);
                            account.sendTransfer(transfer);
                            accountRepository.save(account);
                        },
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender account number does not exist");
                        });

        accountRepository.getAccountByAccountNumber(createTransfer.getReceiverAccountNumber())
                .ifPresentOrElse(account -> {
                            transfer.setReceiver(account);
                            accountRepository.save(account);
                        },
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver account number does not exist");
                        });

        return transferRepository.save(transfer).getId();
    }

    public boolean cancelTransfer(String transactionId) {
        Optional<Transfer> transferOptional = transferRepository.getTransferById(Long.parseLong(transactionId));
        transferOptional.ifPresentOrElse(transfer -> {
                    if (DAYS.between(transfer.getTransferDate(), LocalDate.now()) < 7 && transfer.getState() == State.CREATED) {
                        transfer.setState(State.CANCELLED);
                        transferRepository.save(transfer);
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer cannot be cancelled after 7 days of creation");
                    }
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer does not exist");
                });

        return true;
    }
}
