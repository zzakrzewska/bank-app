package mas.s24365_projekt_koncowy.repository;

import mas.s24365_projekt_koncowy.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    public Optional<Transfer> getTransferById(Long transferId);
}
