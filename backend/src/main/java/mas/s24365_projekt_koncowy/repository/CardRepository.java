package mas.s24365_projekt_koncowy.repository;

import mas.s24365_projekt_koncowy.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
