package mas.s24365_projekt_koncowy.repository;

import mas.s24365_projekt_koncowy.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByPESEL(String PESEL);
}
