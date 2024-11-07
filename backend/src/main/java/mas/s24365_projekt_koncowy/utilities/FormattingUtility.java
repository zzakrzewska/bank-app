package mas.s24365_projekt_koncowy.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FormattingUtility {

    public static String formatTelephoneNumber(String telephoneNumber) {
        if (!telephoneNumber.matches("^[0-9]{9}$")) {
            throw new IllegalArgumentException("Wrong telephone number format");
        }

        return telephoneNumber.substring(0, 3) + " " +
                telephoneNumber.substring(3, 6) + " " +
                telephoneNumber.substring(6);
    }

    public static String formatBankAccount(String bankAccount) {
        if (!bankAccount.matches("^(PL)[0-9]{26}$")) {
            throw new IllegalArgumentException("Wrong IBAN account number format");
        }

        return bankAccount.substring(0, 4) + " " +
                bankAccount.substring(4, 8) + " " +
                bankAccount.substring(8, 12) + " " +
                bankAccount.substring(12, 16) + " " +
                bankAccount.substring(16, 20) + " " +
                bankAccount.substring(20, 24) + " " +
                bankAccount.substring(24);
    }

    public static String formatAmount(Double amount) {
        return String.format("%.2f", amount);
    }
}
