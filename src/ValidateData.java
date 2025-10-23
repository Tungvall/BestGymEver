import java.time.LocalDate;
import java.util.Arrays;

public class ValidateData {


    public String validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("Name is null or empty.");
        }
        name = name.trim();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && c != '-') {
                throw new IllegalArgumentException("Invalid character provided with string.");
            }
        }
        name = name.toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    boolean validateEmail(String email) {

        email = email.trim();

        if (!email.contains("@")) {
            return false;
        }
        int positionOfAt = email.indexOf("@");
        int positionOfLastDot = email.lastIndexOf(".");
        String local = email.substring(0, positionOfAt);
        String domain = email.substring(positionOfAt + 1, positionOfLastDot);
        String tldomain = email.substring(positionOfLastDot + 1);

        if (email == null || email.isEmpty()) {
            return false;
        }
        if (local.startsWith(".") || local.endsWith(".")) {
            return false;
        }
        if (email.lastIndexOf(".") == email.length() - 1) {
            return false;
        }
        if (email.contains("..")) {
            return false;
        }
        if (domain.length() == 0) {
            return false;
        }
        return true;
    }

    public boolean validateSocialSecNumber(String SSN) {
        int posOfDash;
        if (SSN == null || SSN.isEmpty()) {
            return false;
        }
        if (SSN.contains("-")) {
            posOfDash = SSN.indexOf("-");
        } else {
            return false;
        }
        for (int i = 0; i < SSN.length(); i++) {
            char c = SSN.charAt(i);
            if (i != 6) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateDate(String date) {
        if (date == null || date.isEmpty()) {
            return false;
        }

        try {
            LocalDate.parse(date);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean validateMemberType(String memberType) {
        if (memberType == null || memberType.isEmpty()) {
            return false;
        }
        return switch (memberType) {
            case "Standard", "Guld", "Platina" -> true;
            default -> false;
        };
    }

    public boolean validateHeaderFormat(String line) {
        String[] header = line.split(";");
        String[] expected = {
                "Namn",
                "Adress",
                "Mailadress",
                "Personnummer",
                "Datum_köpt_gymmedlemskap",
                "Datum_senast_uppdaterad",
                "Medlemsnivå"};
        if (Arrays.equals(expected, header)) {
            return true;
        }
        return false;
    }


    public static boolean validateActiveMember(LocalDate lastPayment) {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        return !lastPayment.isBefore(oneYearAgo);
    }

}
