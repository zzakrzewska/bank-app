package mas.s24365_projekt_koncowy.repository;

import mas.s24365_projekt_koncowy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> getAccountByAccountNumber(String accountNumber);
}
