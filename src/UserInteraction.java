import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserInteraction {

    private final ReadFile rf;
    private final WriteFile wf;

    public UserInteraction(ReadFile rf, WriteFile wf) {
        this.rf = rf;
        this.wf = wf;
    }

    void displayPrompt() {
        while (true) {
            StringBuilder sb = new StringBuilder();
            List<Person> searchResult = new ArrayList<>();
            List<Person> memberList = rf.getMembers();

            String searchString = JOptionPane.showInputDialog(null, "Search user:");
            if (searchString == null) {
                System.exit(0);
            }

            searchString = searchString.toLowerCase();
            String currentMemberStatus = "Membership Cancelled";

            for (Person p : memberList) {
                String fullName = p.getFirstName() + " " + p.getLastName();
                if (fullName.toLowerCase().contains(searchString) || p.getSocialSecNumber().contains(searchString)) {
                    searchResult.add(p);
                }
            }

            try {
                if (searchResult.size() < 1) {
                    JOptionPane.showMessageDialog(null, "No person found.");

                } else if (searchResult.size() == 1) {
                    Person user = searchResult.get(0);
                    if (ValidateData.validateActiveMember(user.getMembershipRenewDate())) {
                        currentMemberStatus = user.getMembershipType();
                    }
                    int checkIn = JOptionPane.showConfirmDialog(null,
                            "Membership: " + currentMemberStatus + "\n"
                                    + user.getFirstName() + " " + user.getLastName() + " (" + user.getSocialSecNumber() + ")\n\n"
                                    + user.getAddress() + "\n"
                                    + user.getEmail() + "\n"
                                    + "Membership start: " + user.getMembershipStartDate() + "\n"
                                    + "Membership renewed: " + user.getMembershipRenewDate(), "Check in member?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (checkIn == JOptionPane.YES_OPTION) {
                        if (wf.logUser(user)) {
                            JOptionPane.showMessageDialog(null, user.getFirstName() + " " + user.getLastName() + " checkd in!");
                        } else {
                            JOptionPane.showMessageDialog(null, "An error occured!");
                        }
                    }

                } else {
                    sb.append("More than one person found with the same name.\n");
                    for (Person u : searchResult) {
                        sb.append(u.getFirstName() + " " + u.getLastName() + "(" + u.getSocialSecNumber() + ")\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Invalid input.");
            }
        }
    }
}
