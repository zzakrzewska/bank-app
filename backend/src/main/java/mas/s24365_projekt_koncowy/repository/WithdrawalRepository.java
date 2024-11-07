package mas.s24365_projekt_koncowy.repository;

import mas.s24365_projekt_koncowy.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}
