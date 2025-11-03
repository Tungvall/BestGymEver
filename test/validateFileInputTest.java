import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidateDataTest {
    String filePath = "test_members.txt";
    ValidateData x = new ValidateData();
    ReadFile r = new ReadFile(x, filePath);

    @Test
    void validateNameTest() {

        String expected = "Mikael";
        String leadingSpace = " Mikael";
        String trailingSpace = "Mikael ";
        String leadingAndTrailingSpace = " Mikael ";
        String allLowerCase = "mikael";
        String allUpperCase = "MIKAEL";
        String containsNumber = "Mikael5";
        String emptyString = "";

        assertThrows(NullPointerException.class, () -> x.validateName(emptyString));
        assertEquals(expected, x.validateName(leadingSpace));
        assertEquals(expected, x.validateName(trailingSpace));
        assertThrows(IllegalArgumentException.class, () -> x.validateName(containsNumber));
        assertEquals(expected, x.validateName(leadingAndTrailingSpace));
        assertEquals(expected, x.validateName(allLowerCase));
        assertEquals(expected, x.validateName(allUpperCase));
    }

    @Test
    void validateEmailTest() {

        assertTrue(x.validateEmail("mikael@gmail.com"));
        assertFalse(x.validateEmail("mikaelgmail.com."));
        assertFalse(x.validateEmail(".mikael@gmail.com"));
        assertFalse(x.validateEmail("mikael.@gmail.com"));
        assertFalse(x.validateEmail("mikael@.gmail.com."));
        assertFalse(x.validateEmail("mik..ael@gmail.com"));
        assertFalse(x.validateEmail("mikael@gmail."));
        assertFalse(x.validateEmail("mikael@.com"));

    }

    @Test
    void validateSocialSecurityNumberTest() {

        String expected = "580606-9828";

        assertTrue(x.validateSocialSecNumber("580606-9828"));
        assertFalse(x.validateSocialSecNumber("58060-69828"));
        assertFalse(x.validateSocialSecNumber("580606982"));
        assertFalse(x.validateSocialSecNumber("58060698289"));
    }

    @Test
    void validateDateTest() {
        assertTrue(x.validateDate("2022-01-21"));
        assertFalse(x.validateDate("2022-01-42"));
    }

    @Test
    void validateMemberTypeTest() {
        assertTrue(x.validateMemberType("Standard"));
        assertTrue(x.validateMemberType("Gold"));
        assertTrue(x.validateMemberType("Platina"));
        assertFalse(x.validateMemberType("Diamond"));
        assertFalse(x.validateMemberType(""));
    }

    @Test
    void validateActiveMemberTest() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        LocalDate nineMonthAgo = LocalDate.now().minusMonths(9);
        LocalDate oneYearAndADay = LocalDate.now().minusMonths(12).minusDays(1);
        assertTrue(x.validateActiveMember(oneMonthAgo));
        assertTrue(x.validateActiveMember(nineMonthAgo));
        assertFalse(x.validateActiveMember(oneYearAndADay));
    }

    @Test
    void csvHeaderFormatTest() {
        assertTrue(x.validateHeaderFormat("Namn;Adress;Mailadress;Personnummer;Datum_köpt_gymmedlemskap;Datum_senast_uppdaterad;Medlemsnivå"));
        assertFalse(x.validateHeaderFormat("Namn;;Mailadress;Personnummer;Datum_köpt_gymmedlemskap;Datum_senast_uppdaterad;Medlemsnivå"));
        assertFalse(x.validateHeaderFormat("Namn;;Mailadress;Personnummer;Datum_köpt_gymmedlemskap;Datum_senast_uppdaterad;;Medlemsnivå"));
    }

    @Test
    void csvVerifyColumnTest() throws IOException {
        Path filePath = Path.of("members.txt");
        assertTrue(Files.exists(filePath), "File should exist");

        List<String> lines = Files.readAllLines(filePath);
        assertTrue(lines.size() > 0, "Should have at least one line");

        int expectedColumns = lines.get(0).split(";").length;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int columns = line.split(";").length;
            assertEquals(expectedColumns, columns, "Incorrect number of columns at " + (i + 1) + ".");
        }
    }

    @Test
    void getMembersTest() {
        Person p = new Person("Fredrik", "Berggren", "Skolgränd 8, 16819 Norrköping", "fredde@fakemail.se", "851020-6728", "2019-12-30", "2021-12-30", "Platina");
        List<Person> plist = r.getMembers();
        System.out.println(plist);
        String actual = plist.getFirst().toString();
        String expected = p.toString();
        assertEquals(expected, actual);
        assertNotEquals(null, actual);
        assertNotEquals(expected, "Fredrik Berggren;Skolgränd 8, 16819 Norrköping;fredde@fakemail.se;851020-6728;2019-12-30;2021-12-30;Platin");

    }

    @Test
    void csvVerifyRead() {
        String expected = "Fredrik Berggren;Skolgränd 8, 16819 Norrköping;fredde@fakemail.se;851020-6728;2019-12-30;2021-12-30;Platin";
    }

}

