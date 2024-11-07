package mas.s24365_projekt_koncowy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Director extends Person {

    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
    final Set<Clerk> employees = new HashSet<>();
}
