package mas.s24365_projekt_koncowy.repository;

import mas.s24365_projekt_koncowy.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
    @Query("SELECT b FROM Bank b " +
            "JOIN FETCH b.accounts a " +
            "JOIN FETCH a.accountOwners ao " +
            "JOIN FETCH ao.owner p " +
            "WHERE b.BIC = :bic")
    public Optional<Bank> findBankClientsByBIC(@Param("bic") String bic);

}
