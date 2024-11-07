package mas.s24365_projekt_koncowy.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$")
    @Column(unique = true)
    @Schema(description = "Bank BIC number", example = "BREXPLPWMBK")
    String BIC;

    @NotBlank
    @Size(min = 1, max = 50)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Address", nullable = false)
    Address address;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    final Set<ATM> atms = new HashSet<>();

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    final Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    final Set<BankEmployee> bankEmployees = new HashSet<>();
}
