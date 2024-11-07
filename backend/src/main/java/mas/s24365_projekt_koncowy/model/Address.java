package mas.s24365_projekt_koncowy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    Long id;

    @NotBlank
    @Size(min = 1, max = 30)
    String city;
    @NotBlank
    @Size(min = 1, max = 30)
    String street;
    @NotBlank
    @Size(min = 1, max = 30)
    String building;
    @Size(max = 30)
    String apartment;
    @NotBlank
    @Size(min = 1, max = 30)
    String postalCode;

    public String toString() {
        String base = postalCode + " " + city + ", " + street + " " + building;
        return apartment == null ? base : base + "/" + apartment;
    }
}
