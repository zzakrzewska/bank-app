package mas.s24365_projekt_koncowy.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import mas.s24365_projekt_koncowy.model.Account;
import mas.s24365_projekt_koncowy.model.Bank;
import mas.s24365_projekt_koncowy.model.Person;
import mas.s24365_projekt_koncowy.model.dto.client.Client;
import mas.s24365_projekt_koncowy.repository.BankRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BankService {

    BankRepository bankRepository;

    public List<Client> getBankClients(String bankBIC) {
        Optional<Bank> bank = bankRepository.findBankClientsByBIC(bankBIC);

        if (bank.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank with given BIC not found");
        }

        List<Client> clients = new ArrayList<>();
        Set<Account> accounts = bank.get().getAccounts();
        Set<Person> owners = new HashSet<>();
        accounts.forEach(account -> account.getAccountOwners().forEach(owner -> owners.add(owner.getOwner())));

        if (owners.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Bank has no clients associated with it");
        }

        for (Person person : owners) {
            clients.add(Client.builder()
                    .pesel(person.getPESEL())
                    .name(person.getName())
                    .surname(person.getSurname())
                    .build());
        }

        return clients;
    }
}
