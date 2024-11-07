package mas.s24365_projekt_koncowy.configuration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import mas.s24365_projekt_koncowy.model.*;
import mas.s24365_projekt_koncowy.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    AccountOwnerRepository accountOwnerRepository;
    AccountRepository accountRepository;
    AddressRepository addressRepository;
    ATMRepository atmRepository;
    BankRepository bankRepository;
    CardRepository cardRepository;
    PersonRepository personRepository;
    TransferRepository transferRepository;
    WithdrawalRepository withdrawalRepository;

    Set<Type> BOTH_TYPES = Set.of(Type.EMPLOYEE, Type.CLIENT);
    Set<Type> CLIENT = Set.of(Type.CLIENT);
    Set<Type> EMPLOYEE = Set.of(Type.EMPLOYEE);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeData();
    }

    private void initializeData() {
        Address address1 = Address.builder()
                .city("Warszawa")
                .street("Morelowa")
                .building("64A")
                .apartment("20")
                .postalCode("02-225")
                .build();

        Address address2 = Address.builder()
                .city("Warszawa")
                .street("Wrocławska")
                .building("11")
                .apartment("2")
                .postalCode("02-467")
                .build();

        Address address3 = Address.builder()
                .city("Gdynia")
                .street("Morska")
                .building("55")
                .postalCode("80-299")
                .build();

        Address address4 = Address.builder()
                .city("Kielce")
                .street("Solna")
                .building("127")
                .postalCode("25-007")
                .build();

        Address address5 = Address.builder()
                .city("Kraków")
                .street("Długa")
                .building("35")
                .postalCode("31-147")
                .build();

        Address address6 = Address.builder()
                .city("Poznań")
                .street("Słowiańska")
                .building("8B")
                .apartment("3")
                .postalCode("60-300")
                .build();

        Address address7 = Address.builder()
                .city("Wrocław")
                .street("Świdnicka")
                .building("10")
                .postalCode("50-066")
                .build();

        Address address8 = Address.builder()
                .city("Łódź")
                .street("Piotrkowska")
                .building("223")
                .postalCode("90-402")
                .build();

        Address address9 = Address.builder()
                .city("Szczecin")
                .street("Wojska Polskiego")
                .building("15")
                .apartment("4")
                .postalCode("70-476")
                .build();

        Address address10 = Address.builder()
                .city("Gdańsk")
                .street("Podwale Przedmiejskie")
                .building("3")
                .apartment("7")
                .postalCode("80-895")
                .build();

        if (addressRepository.count() == 0) {
            addressRepository.saveAll(
                    List.of(address1, address2, address3, address4, address5,
                            address6, address7, address8, address9, address10));
        }

        Person person1 = Person.builder()
                .name("Anna")
                .surname("Kowalczyk")
                .PESEL("00260575225")
                .telephoneNumber("605123849")
                .mail("akowalczyk@wp.pl")
                .types(BOTH_TYPES)
                .ownedAccounts(new HashSet<>())
                .workMail("ak@bank.pl")
                .skills(Set.of("o365", "credit risk analysis"))
                .password("haslo")
                .address(address1)
                .build();

        Person person2 = Person.builder()
                .name("Dominika")
                .surname("Adamczyk")
                .PESEL("91031565422")
                .telephoneNumber("600111222")
                .mail("dadamska@gmail.com")
                .types(BOTH_TYPES)
                .ownedAccounts(new HashSet<>())
                .workMail("da@bank.com")
                .skills(Set.of("Customer service", "Cash handling", "Account management"))
                .password("haslo")
                .address(address5)
                .build();

        Person person3 = Person.builder()
                .name("Artur")
                .surname("Pawlak")
                .PESEL("89043098795")
                .telephoneNumber("503333444")
                .mail("apawlak@hotmail.com")
                .types(BOTH_TYPES)
                .ownedAccounts(new HashSet<>())
                .workMail("ap@banking.com")
                .skills(Set.of("Financial products", "Loan processing", "Risk assessment"))
                .password("haslo")
                .address(address6)
                .build();

        Person person4 = Person.builder()
                .name("Monika")
                .surname("Michalak")
                .PESEL("90053087664")
                .telephoneNumber("512345678")
                .mail("mmichalak@yahoo.com")
                .types(BOTH_TYPES)
                .ownedAccounts(new HashSet<>())
                .workMail("mm@bank.pl")
                .skills(Set.of("Credit analysis", "Investment advice", "Bank regulations"))
                .password("haslo")
                .address(address7)
                .build();

        Person person5 = Person.builder()
                .name("Krzysztof")
                .surname("Kaczmarek")
                .PESEL("88123098715")
                .telephoneNumber("601234567")
                .mail("kkaczmarek@outlook.com")
                .types(BOTH_TYPES)
                .ownedAccounts(new HashSet<>())
                .workMail("kk@banking.com")
                .skills(Set.of("Customer relationships", "Financial planning", "Sales of banking products"))
                .password("haslo")
                .address(address8)
                .build();

        Person person6 = Person.builder()
                .name("Malwina")
                .surname("Nowakowska")
                .PESEL("88123118765")
                .telephoneNumber("602234567")
                .mail("mnowakowska@wp.com")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("haslo")
                .address(address8)
                .build();

        Person person7 = Person.builder()
                .name("Damian")
                .surname("Kowalski")
                .PESEL("90010112315")
                .telephoneNumber("512345678")
                .mail("dkowalski@gmail.com")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("clientpass")
                .address(address9)
                .build();

        Person person8 = Person.builder()
                .name("Kinga")
                .surname("Wójcik")
                .PESEL("92020223466")
                .telephoneNumber("601234567")
                .mail("kwojcik@hotmail.com")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("123456")
                .address(address10)
                .build();

        Person person9 = Person.builder()
                .name("Bartosz")
                .surname("Lis")
                .PESEL("91030334597")
                .telephoneNumber("503456789")
                .mail("blis@yahoo.com")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("bartek123")
                .address(address1)
                .build();

        Person person10 = Person.builder()
                .name("Aleksandra")
                .surname("Jankowska")
                .PESEL("88121245688")
                .telephoneNumber("600987654")
                .mail("ajankowska@outlook.com")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("alex123")
                .address(address2)
                .build();

        Person person11 = Person.builder()
                .name("Maciej")
                .surname("Szymański")
                .PESEL("90010156759")
                .telephoneNumber("601234567")
                .mail("mszymanski@wp.pl")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("maciek123")
                .address(address3)
                .build();

        Person person12 = Person.builder()
                .name("Julia")
                .surname("Witkowska")
                .PESEL("92030367820")
                .telephoneNumber("602345678")
                .mail("jwitkowska@gmail.com")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("julia123")
                .address(address4)
                .build();

        Person person13 = Person.builder()
                .name("Patryk")
                .surname("Kaczmarek")
                .PESEL("91010178971")
                .telephoneNumber("503456789")
                .mail("pkaczmarek@hotmail.com")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("patryk123")
                .address(address5)
                .build();

        Person person14 = Person.builder()
                .name("Oliwia")
                .surname("Michalska")
                .PESEL("88121289002")
                .telephoneNumber("600987654")
                .mail("omichalska@yahoo.com")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("oliwia123")
                .address(address6)
                .build();

        Person person15 = Person.builder()
                .name("Tomasz")
                .surname("Nowak")
                .PESEL("90020290113")
                .telephoneNumber("601234567")
                .mail("tnowak@outlook.com")
                .types(CLIENT)
                .ownedAccounts(new HashSet<>())
                .password("tom123")
                .address(address7)
                .build();

        Person person16 = Person.builder()
                .name("Tadeusz")
                .surname("Kufel")
                .PESEL("90020290123")
                .telephoneNumber("601234111")
                .mail("tadek@o2.pl")
                .workMail("tk@bank.pl")
                .skills(Set.of("Customer relationships", "Financial planning"))
                .types(EMPLOYEE)
                .password("tadek")
                .address(address7)
                .build();

        Person person17 = Person.builder()
                .name("Zofia")
                .surname("Nowak")
                .PESEL("91010134567")
                .telephoneNumber("512345222")
                .mail("znowak@gmail.com")
                .workMail("zn@banking.com")
                .skills(Set.of("Financial products", "Loan processing", "Risk assessment"))
                .types(EMPLOYEE)
                .password("zofia123")
                .address(address8)
                .build();

        Person person18 = Person.builder()
                .name("Wojciech")
                .surname("Kowalski")
                .PESEL("89043045678")
                .telephoneNumber("503333333")
                .mail("wkowalski@hotmail.com")
                .workMail("wk@bank.pl")
                .skills(Set.of("Credit analysis", "Investment advice", "Bank regulations"))
                .types(EMPLOYEE)
                .password("wojtek123")
                .address(address9)
                .build();

        Person person19 = Person.builder()
                .name("Gabriela")
                .surname("Pawlak")
                .PESEL("90053056789")
                .telephoneNumber("512345444")
                .mail("gpawlak@yahoo.com")
                .workMail("gp@banking.com")
                .skills(Set.of("Customer service", "Cash handling", "Account management"))
                .types(EMPLOYEE)
                .password("gabi123")
                .address(address10)
                .build();

        Person person20 = Person.builder()
                .name("Adam")
                .surname("Wójcik")
                .PESEL("88121267890")
                .telephoneNumber("600987555")
                .mail("awojcik@outlook.com")
                .workMail("aw@bank.com")
                .skills(Set.of("Customer relationships", "Financial planning", "Sales of banking products"))
                .types(EMPLOYEE)
                .password("adam123")
                .address(address1)
                .build();


        if (personRepository.count() == 0) {
            personRepository.saveAll(
                    List.of(person1, person2, person3, person4, person5, person6, person7, person8, person9, person10,
                            person11, person12, person13, person14, person15, person16, person17, person18, person19, person20));
        }

        Bank bank1 = Bank.builder()
                .BIC("BREXPLPWNBK")
                .name("nBank")
                .address(address1)
                .build();

        Bank bank2 = Bank.builder()
                .BIC("EGRIPLPRXXX")
                .name("Credit Egricole")
                .address(address7)
                .build();

        if (bankRepository.count() == 0) {
            bankRepository.saveAll(List.of(bank1, bank2));
        }

        Account account1 = Account.builder()
                .accountNumber("PL18205672239122747035319281")
                .balance(20294.54)
                .bank(bank1)
                .build();

        Account account2 = Account.builder()
                .accountNumber("PL18205672239122747035319282")
                .balance(15000.0)
                .bank(bank1)
                .build();

        Account account3 = Account.builder()
                .accountNumber("PL18205672239122747035319283")
                .balance(5570.0)
                .bank(bank2)
                .build();

        Account account4 = Account.builder()
                .accountNumber("PL18205672239122747035319284")
                .balance(30000.0)
                .bank(bank1)
                .build();

        Account account5 = Account.builder()
                .accountNumber("PL18205672239122747035319285")
                .balance(10000.0)
                .bank(bank2)
                .build();

        Account account6 = Account.builder()
                .accountNumber("PL18205672239122747035319286")
                .balance(8000.0)
                .bank(bank1)
                .build();

        Account account7 = Account.builder()
                .accountNumber("PL18205672239122747035319287")
                .balance(25000.0)
                .bank(bank2)
                .build();

        Account account8 = Account.builder()
                .accountNumber("PL18205672239122747035319288")
                .balance(18000.0)
                .bank(bank1)
                .build();

        Account account9 = Account.builder()
                .accountNumber("PL18205672239122747035319289")
                .balance(12000.0)
                .bank(bank2)
                .build();

        AccountOwner accountOwner1 = AccountOwner.builder()
                .owner(person1)
                .account(account1)
                .role(Role.PRIMARY)
                .build();

        AccountOwner accountOwner2 = AccountOwner.builder()
                .owner(person2)
                .account(account1)
                .role(Role.SECONDARY)
                .build();

        AccountOwner accountOwner3 = AccountOwner.builder()
                .owner(person3)
                .account(account2)
                .role(Role.PRIMARY)
                .build();

        AccountOwner accountOwner4 = AccountOwner.builder()
                .owner(person4)
                .account(account2)
                .role(Role.SECONDARY)
                .build();

        AccountOwner accountOwner5 = AccountOwner.builder()
                .owner(person5)
                .account(account3)
                .role(Role.PRIMARY)
                .build();

        AccountOwner accountOwner6 = AccountOwner.builder()
                .owner(person6)
                .account(account3)
                .role(Role.SECONDARY)
                .build();

        AccountOwner accountOwner7 = AccountOwner.builder()
                .owner(person7)
                .account(account4)
                .role(Role.PRIMARY)
                .build();

        AccountOwner accountOwner8 = AccountOwner.builder()
                .owner(person8)
                .account(account4)
                .role(Role.SECONDARY)
                .build();

        AccountOwner accountOwner9 = AccountOwner.builder()
                .owner(person9)
                .account(account5)
                .role(Role.PRIMARY)
                .build();

        AccountOwner accountOwner10 = AccountOwner.builder()
                .owner(person10)
                .account(account5)
                .role(Role.SECONDARY)
                .build();

        AccountOwner accountOwner11 = AccountOwner.builder()
                .owner(person11)
                .account(account6)
                .role(Role.PRIMARY)
                .build();

        AccountOwner accountOwner12 = AccountOwner.builder()
                .owner(person12)
                .account(account6)
                .role(Role.SECONDARY)
                .build();

        AccountOwner accountOwner13 = AccountOwner.builder()
                .owner(person13)
                .account(account7)
                .role(Role.PRIMARY)
                .build();

        AccountOwner accountOwner14 = AccountOwner.builder()
                .owner(person14)
                .account(account8)
                .role(Role.PRIMARY)
                .build();

        AccountOwner accountOwner15 = AccountOwner.builder()
                .owner(person15)
                .account(account9)
                .role(Role.PRIMARY)
                .build();

        AccountOwner accountOwner16 = AccountOwner.builder()
                .owner(person1)
                .account(account2)
                .role(Role.SECONDARY)
                .build();

        Card card1 = DebitCard.builder()
                .cardNumber("4929480012345678")
                .fee(10.0)
                .balance(1000.0)
                .debitAmount(5000.0)
                .owner(account1)
                .build();

        Card card2 = DebitCard.builder()
                .cardNumber("4929123456789012")
                .fee(10.0)
                .balance(10000.0)
                .debitAmount(5000.0)
                .owner(account2)
                .build();

        Card card3 = DebitCard.builder()
                .cardNumber("4929876543210987")
                .fee(10.0)
                .balance(1000.0)
                .debitAmount(5000.0)
                .owner(account3)
                .build();

        Card card4 = DebitCard.builder()
                .cardNumber("4929234567890123")
                .fee(10.0)
                .balance(1582.21)
                .debitAmount(5000.0)
                .owner(account4)
                .build();

        Card card5 = DebitCard.builder()
                .cardNumber("4929765432109876")
                .fee(10.0)
                .balance(2457.12)
                .debitAmount(5000.0)
                .owner(account5)
                .build();

        Card card6 = DebitCard.builder()
                .cardNumber("4929345678901234")
                .fee(10.0)
                .balance(8000.0)
                .debitAmount(5000.0)
                .owner(account6)
                .build();

        Card card7 = DebitCard.builder()
                .cardNumber("4929987654321098")
                .fee(10.0)
                .balance(25000.0)
                .debitAmount(5000.0)
                .owner(account7)
                .build();

        Card card8 = DebitCard.builder()
                .cardNumber("4929456789012345")
                .fee(10.0)
                .balance(18000.0)
                .debitAmount(5000.0)
                .owner(account8)
                .build();

        Card card9 = CreditCard.builder()
                .cardNumber("4929876543210123")
                .fee(10.0)
                .monthlyPayment(500.0)
                .balance(2000.0)
                .owner(account9)
                .build();

        Card card10 = CreditCard.builder()
                .cardNumber("4029123456789012")
                .fee(10.0)
                .balance(10.0)
                .monthlyPayment(400.0)
                .owner(account1)
                .build();

        Card card11 = CreditCard.builder()
                .cardNumber("4029876543210987")
                .fee(10.0)
                .balance(0.0)
                .monthlyPayment(400.0)
                .owner(account2)
                .build();

        Card card12 = CreditCard.builder()
                .cardNumber("4029234567890123")
                .fee(10.0)
                .monthlyPayment(800.0)
                .balance(2000.0)
                .owner(account3)
                .build();

        Card card13 = DebitCard.builder()
                .cardNumber("4029765432109876")
                .fee(10.0)
                .balance(8000.0)
                .debitAmount(5000.0)
                .owner(account4)
                .build();

        Card card14 = DebitCard.builder()
                .cardNumber("4029345678901234")
                .fee(10.0)
                .balance(1289.44)
                .debitAmount(5000.0)
                .owner(account5)
                .build();


        Transfer transfer1 = Transfer.builder()
                .sender(account1)
                .receiver(account2)
                .amount(500.0)
                .state(State.PROCESSED)
                .transferDate(LocalDate.of(2020, 12, 11))
                .build();

        Transfer transfer2 = Transfer.builder()
                .sender(account2)
                .receiver(account3)
                .amount(1200.0)
                .state(State.CANCELLED)
                .transferDate(LocalDate.of(2021, 3, 25))
                .build();

        Transfer transfer3 = Transfer.builder()
                .sender(account3)
                .receiver(account4)
                .amount(300.0)
                .state(State.PROCESSED)
                .transferDate(LocalDate.of(2021, 5, 7))
                .build();

        Transfer transfer4 = Transfer.builder()
                .sender(account4)
                .receiver(account5)
                .amount(1000.0)
                .state(State.CREATED)
                .transferDate(LocalDate.of(2021, 7, 14))
                .build();

        Transfer transfer5 = Transfer.builder()
                .sender(account5)
                .receiver(account6)
                .amount(700.0)
                .state(State.RECALLED)
                .transferDate(LocalDate.of(2021, 8, 30))
                .build();

        Transfer transfer6 = Transfer.builder()
                .sender(account6)
                .receiver(account7)
                .amount(1500.0)
                .state(State.PROCESSED)
                .transferDate(LocalDate.of(2021, 9, 5))
                .build();

        Transfer transfer7 = Transfer.builder()
                .sender(account7)
                .receiver(account8)
                .amount(200.0)
                .state(State.PROCESSED)
                .transferDate(LocalDate.of(2021, 10, 12))
                .build();

        Transfer transfer8 = Transfer.builder()
                .sender(account8)
                .receiver(account9)
                .amount(900.0)
                .state(State.CANCELLED)
                .transferDate(LocalDate.of(2021, 11, 18))
                .build();

        Transfer transfer9 = Transfer.builder()
                .sender(account9)
                .receiver(account1)
                .amount(400.0)
                .state(State.CREATED)
                .transferDate(LocalDate.of(2021, 12, 21))
                .build();

        Transfer transfer10 = Transfer.builder()
                .sender(account1)
                .receiver(account3)
                .amount(800.0)
                .state(State.PROCESSED)
                .transferDate(LocalDate.of(2022, 1, 5))
                .build();

        Transfer transfer11 = Transfer.builder()
                .sender(account2)
                .receiver(account4)
                .amount(600.0)
                .state(State.RECALLED)
                .transferDate(LocalDate.of(2022, 2, 9))
                .build();

        Transfer transfer12 = Transfer.builder()
                .sender(account3)
                .receiver(account5)
                .amount(1100.0)
                .state(State.PROCESSED)
                .transferDate(LocalDate.of(2022, 3, 15))
                .build();

        Transfer transfer13 = Transfer.builder()
                .sender(account4)
                .receiver(account6)
                .amount(950.0)
                .state(State.CREATED)
                .transferDate(LocalDate.of(2022, 4, 20))
                .build();

        Transfer transfer14 = Transfer.builder()
                .sender(account5)
                .receiver(account7)
                .amount(300.0)
                .state(State.PROCESSED)
                .transferDate(LocalDate.of(2022, 5, 25))
                .build();

        Transfer transfer15 = Transfer.builder()
                .sender(account6)
                .receiver(account8)
                .amount(2000.0)
                .state(State.CANCELLED)
                .transferDate(LocalDate.of(2022, 6, 30))
                .build();

        Transfer transfer16 = Transfer.builder()
                .sender(account7)
                .receiver(account9)
                .amount(700.0)
                .state(State.PROCESSED)
                .transferDate(LocalDate.of(2022, 7, 7))
                .build();

        Transfer transfer17 = Transfer.builder()
                .sender(account8)
                .receiver(account1)
                .amount(850.0)
                .state(State.RECALLED)
                .transferDate(LocalDate.of(2022, 8, 12))
                .build();

        Transfer transfer18 = Transfer.builder()
                .sender(account9)
                .receiver(account2)
                .amount(950.0)
                .state(State.PROCESSED)
                .transferDate(LocalDate.of(2022, 9, 18))
                .build();

        Transfer transfer19 = Transfer.builder()
                .sender(account1)
                .receiver(account4)
                .amount(1200.0)
                .state(State.CREATED)
                .transferDate(LocalDate.of(2022, 10, 22))
                .build();

        Transfer transfer20 = Transfer.builder()
                .sender(account1)
                .receiver(account4)
                .amount(1200.0)
                .state(State.CREATED)
                .transferDate(LocalDate.of(2019, 10, 22))
                .build();

        account1.sendTransfer(transfer1);
        account2.receiveTransfer(transfer1);
        account2.sendTransfer(transfer2);
        account3.receiveTransfer(transfer2);
        account3.sendTransfer(transfer3);
        account4.receiveTransfer(transfer3);
        account4.sendTransfer(transfer4);
        account5.receiveTransfer(transfer4);
        account5.sendTransfer(transfer5);
        account6.receiveTransfer(transfer5);
        account6.sendTransfer(transfer6);
        account7.receiveTransfer(transfer6);
        account7.sendTransfer(transfer7);
        account8.receiveTransfer(transfer7);
        account8.sendTransfer(transfer8);
        account9.receiveTransfer(transfer8);
        account9.sendTransfer(transfer9);
        account1.receiveTransfer(transfer9);
        account1.sendTransfer(transfer10);
        account3.receiveTransfer(transfer10);
        account2.sendTransfer(transfer11);
        account4.receiveTransfer(transfer11);
        account3.sendTransfer(transfer12);
        account5.receiveTransfer(transfer12);
        account4.sendTransfer(transfer13);
        account6.receiveTransfer(transfer13);
        account5.sendTransfer(transfer14);
        account7.receiveTransfer(transfer14);
        account6.sendTransfer(transfer15);
        account8.receiveTransfer(transfer15);
        account7.sendTransfer(transfer16);
        account9.receiveTransfer(transfer16);
        account8.sendTransfer(transfer17);
        account1.receiveTransfer(transfer17);
        account9.sendTransfer(transfer18);
        account2.receiveTransfer(transfer18);
        account1.sendTransfer(transfer19);
        account4.receiveTransfer(transfer19);

        account1.addAccountOwner(accountOwner1);
        account1.addAccountOwner(accountOwner2);
        account2.addAccountOwner(accountOwner3);
        account2.addAccountOwner(accountOwner4);
        account3.addAccountOwner(accountOwner5);
        account3.addAccountOwner(accountOwner6);
        account4.addAccountOwner(accountOwner7);
        account4.addAccountOwner(accountOwner8);
        account5.addAccountOwner(accountOwner9);
        account5.addAccountOwner(accountOwner10);
        account6.addAccountOwner(accountOwner11);
        account6.addAccountOwner(accountOwner12);
        account7.addAccountOwner(accountOwner13);
        account8.addAccountOwner(accountOwner14);
        account9.addAccountOwner(accountOwner15);
        account2.addAccountOwner(accountOwner16);

        account1.addCard(card1);
        account2.addCard(card2);
        account3.addCard(card3);
        account4.addCard(card4);
        account5.addCard(card5);
        account6.addCard(card6);
        account7.addCard(card7);
        account8.addCard(card8);
        account9.addCard(card9);
        account1.addCard(card10);
        account2.addCard(card11);
        account3.addCard(card12);
        account4.addCard(card13);
        account5.addCard(card14);


        if (accountRepository.count() == 0) {
            accountRepository.saveAll(
                    List.of(account1, account2, account3, account4,
                            account5, account6, account7, account8, account9));
        }

        if (cardRepository.count() == 0) {
            cardRepository.saveAll(
                    List.of(card1, card2, card3, card4, card5, card6, card7,
                            card8, card9, card10, card11, card12, card13, card14)
            );
        }

        if (accountOwnerRepository.count() == 0) {
            accountOwnerRepository.saveAll(
                    List.of(accountOwner1, accountOwner2, accountOwner3, accountOwner4, accountOwner5, accountOwner6,
                            accountOwner7, accountOwner8, accountOwner9, accountOwner10, accountOwner11, accountOwner12,
                            accountOwner13, accountOwner14, accountOwner15, accountOwner16));
        }

        if (transferRepository.count() == 0) {
            transferRepository.saveAll(
                    List.of(transfer1, transfer2, transfer3, transfer4, transfer5, transfer6, transfer7, transfer8,
                            transfer9, transfer10, transfer11, transfer12, transfer13, transfer14, transfer15,
                            transfer16, transfer17, transfer18, transfer19, transfer20));
        }

        ATM atm1 = ATM.builder()
                .address(address1)
                .owner(bank1)
                .amount(100000.0)
                .build();

        ATM atm2 = ATM.builder()
                .address(address2)
                .owner(bank1)
                .amount(120000.0)
                .build();

        ATM atm3 = ATM.builder()
                .address(address3)
                .owner(bank2)
                .amount(80000.0)
                .build();

        ATM atm4 = ATM.builder()
                .address(address4)
                .owner(bank1)
                .amount(150000.0)
                .build();

        ATM atm5 = ATM.builder()
                .address(address5)
                .owner(bank2)
                .amount(90000.0)
                .build();

        Withdrawal withdrawal1 = Withdrawal.builder()
                .atm(atm1)
                .amount(50.0)
                .card(card2)
                .date(LocalDate.of(2024, 11, 11))
                .build();

        if (atmRepository.count() == 0) {
            atmRepository.saveAll(List.of(atm1, atm2, atm3, atm4, atm5));
        }


    }
}
