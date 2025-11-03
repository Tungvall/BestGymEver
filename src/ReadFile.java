import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    private final ValidateData validate;
    Path textFile;

    public ReadFile(ValidateData validate, String filePath) {
        this.validate = validate;
        this.textFile = Paths.get(filePath);
    }

    public List<Person> getMembers() {

        List<Person> members = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(this.textFile)) {

            if (Files.size(this.textFile) == 0) {
                JOptionPane.showMessageDialog(null, "File is empty.", "Error occurred!", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            String line = br.readLine();
            if (!validate.validateHeaderFormat(line)) {
                JOptionPane.showMessageDialog(null, "Invalid header/format.", "Error occurred!", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }


            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                String firstName = fields[0].substring(0, fields[0].indexOf(" "));
                String lastName = fields[0].substring(fields[0].indexOf(" "));
                String address = fields[1];
                String email = fields[2];
                String socialSecurity = fields[3];
                String memberShipStartDate = fields[4];
                String membershipRenewDate = fields[5];
                String membershipType = fields[6];

                firstName = validate.validateName(firstName);
                lastName = validate.validateName(lastName);
                if (!validate.validateEmail(email)) {
                    email = email + "[?]";
                }

                if (!validate.validateSocialSecNumber(socialSecurity)) {
                    socialSecurity = socialSecurity + "[?]";
                }
                // memberShipStartDate = validate.validateDate(memberShipStartDate);
                // membershipRenewDate = validate.validateDate(membershipRenewDate);

                if (!validate.validateMemberType(membershipType)) {
                    membershipType = membershipType + "[?]";
                }

                members.add(new Person(firstName, lastName, address, email, socialSecurity, memberShipStartDate, membershipRenewDate, membershipType));
            }

        } catch (
                FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());

        } catch (
                IOException e) {
            System.out.println("IOException");
            System.out.println(e.getMessage());
        }
        return members;
    }
}