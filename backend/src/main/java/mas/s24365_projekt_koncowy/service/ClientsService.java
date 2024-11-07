package mas.s24365_projekt_koncowy.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import mas.s24365_projekt_koncowy.model.AccountOwner;
import mas.s24365_projekt_koncowy.model.Person;
import mas.s24365_projekt_koncowy.model.Type;
import mas.s24365_projekt_koncowy.model.dto.client.ClientDetails;
import mas.s24365_projekt_koncowy.model.dto.bank.BankClientAccount;
import mas.s24365_projekt_koncowy.repository.PersonRepository;
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
public class ClientsService {

    PersonRepository personRepository;

    public ClientDetails getClientByPesel(String clientPESEL) {
        Optional<Person> client = personRepository.findByPESEL(clientPESEL);

        if (client.isEmpty() || !client.get().getTypes().contains(Type.CLIENT)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with given ID not found");
        }

        if (client.get().getMail() == null) {
            return ClientDetails.builder()
                    .name(client.get().getName())
                    .surname(client.get().getSurname())
                    .pesel(client.get().getPESEL())
                    .birthDate(client.get().getBirthDate())
                    .age(client.get().getAge())
                    .address(client.get().getAddress().toString())
                    .telephoneNumber(FormattingUtility
                            .formatTelephoneNumber(client.get().getTelephoneNumber()))
                    .build();
        }

        return ClientDetails.builder()
                .name(client.get().getName())
                .surname(client.get().getSurname())
                .pesel(client.get().getPESEL())
                .birthDate(client.get().getBirthDate())
                .age(client.get().getAge())
                .address(client.get().getAddress().toString())
                .telephoneNumber(FormattingUtility
                        .formatTelephoneNumber(client.get().getTelephoneNumber()))
                .mail(client.get().getMail())
                .build();
    }

    public List<BankClientAccount> getClientAccounts(String clientPESEL) {
        Optional<Person> client = personRepository.findByPESEL(clientPESEL);

        if (client.isEmpty() || !client.get().getTypes().contains(Type.CLIENT)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with given ID not found");
        }

        List<BankClientAccount> bankClientAccounts = new ArrayList<>();

        for (AccountOwner account : client.get().getOwnedAccounts()) {
            bankClientAccounts.add(BankClientAccount.builder()
                    .accountNumber(FormattingUtility.formatBankAccount(account.getAccount().getAccountNumber()))
                    .build());
        }

        if (bankClientAccounts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Client has no accounts associated with them");
        }

        return bankClientAccounts;
    }
}
