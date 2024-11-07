package mas.s24365_projekt_koncowy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    @Size(max = 30)
    String name;

    @NotBlank
    @Size(max = 30)
    String surname;

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "^[0-9]{11}$")
    @Column(unique = true)
    String PESEL;

    @NotBlank
    @Size(max = 20)
    String telephoneNumber;

    @Size(max = 30)
    String mail;

    @Size(max = 30)
    String workMail;

    @ElementCollection
    Set<String> skills;

    @ElementCollection(targetClass = Type.class)
    Set<Type> types;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Address", nullable = false)
    Address address;

    @NotBlank
    String password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    Set<AccountOwner> ownedAccounts;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    Set<BankEmployee> employmentHistory;

    public Person from(String name, String surname, String PESEL,
                       String telephoneNumber, String mail, Address address, String password,
                       String workMail, Set<String> skills, Set<Type> types) {
        for (Type t : types) {
            if (t == null) {
                throw new IllegalArgumentException("None of the types can be null");
            }
        }

        if (types.contains(Type.EMPLOYEE)) {
            Person person = new Person(name, surname, PESEL, telephoneNumber, mail, address, password, workMail, skills);
            person.employmentHistory = new HashSet<>();

            if (types.contains(Type.CLIENT)) {
                person.ownedAccounts = new HashSet<>();
                return person;
            }

        } else {
            Person person = new Person(name, surname, PESEL, telephoneNumber, mail, address, password);
            person.ownedAccounts = new HashSet<>();
            return person;
        }

        throw new IllegalArgumentException("Person must have at least one type");
    }

    private Person(String name, String surname, String PESEL,
                   String telephoneNumber, String mail, Address address, String password) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.telephoneNumber = telephoneNumber;
        this.mail = mail;
        this.address = address;
        this.password = password;
    }

    private Person(String name, String surname, String PESEL,
                   String telephoneNumber, String mail, Address address, String password,
                   String workMail, Set<String> skills) {
        this(name, surname, PESEL, telephoneNumber, mail, address, password);
        this.workMail = workMail;

        for (String s : skills) {
            if (s.isBlank()) {
                throw new IllegalArgumentException("None of the skills can be blank");
            }
        }

        this.skills = skills;
        this.types = Set.of(Type.EMPLOYEE);
    }

    public int getAge() {
        LocalDate birthDate = this.getBirthDate();
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public LocalDate getBirthDate() {
        int year = Integer.parseInt(PESEL.substring(0, 2));
        int month = Integer.parseInt(PESEL.substring(2, 4));
        int day = Integer.parseInt(PESEL.substring(4, 6));

        if (month >= 1 && month <= 12) {
            year += 1900;
        } else if (month >= 21 && month <= 32) {
            year += 2000;
            month -= 20;
        }

        return LocalDate.of(year, month, day);
    }

    public void addAccountOwner(AccountOwner accountOwner) {
        if (!this.getTypes().contains(Type.CLIENT)) {
            throw new IllegalArgumentException("An account owner must be a client");
        }

        if (ownedAccounts.contains(accountOwner)) {
            return;
        }

        if (accountOwner == null) {
            throw new IllegalArgumentException("An account owner cannot be null");
        }

        if (accountOwner.getOwner() != this) {
            throw new IllegalArgumentException("An account owner must be connected to the client");
        }

        ownedAccounts.add(accountOwner);
        accountOwner.getAccount().addAccountOwner(accountOwner);
    }

}
