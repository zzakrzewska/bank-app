package mas.s24365_projekt_koncowy.repository;

import mas.s24365_projekt_koncowy.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
