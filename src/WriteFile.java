import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class WriteFile {

    private final ValidateData validate;
    Path textFile;

    public WriteFile(ValidateData validate, String ptLogPath) {
        this.validate = validate;
        this.textFile = Paths.get(ptLogPath);
    }

    public boolean logUser(Person user) {
        String line;
        LocalDate now = LocalDate.now();
        StringBuilder sb = new StringBuilder();

        sb.append(user.getFirstName()).append(" ").append(user.getLastName()).append(";").append(user.getSocialSecNumber()).append(";").append(now);
        line = sb.toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.textFile.toFile(), true))) {
            if (Files.size(this.textFile) == 0) {
                try {
                    writer.write("Namn;Personnummer;Incheckad;");
                    writer.newLine();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            writer.write(line);
            writer.newLine();

        } catch (IOException e) {
            return false;
        }
        return true;
    }

}



