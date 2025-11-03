import java.time.LocalDate;

public class Person {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private final String socialSecNumber;
    private final LocalDate membershipStartDate;
    private final LocalDate membershipRenewDate;
    private final String membershipType;

    public Person(String firstName, String lastName,String address, String email, String socialSecNumber, String membershipStartDate, String membershipRenewDate, String membershipType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address =address;
        this.email = email;
        this.socialSecNumber = socialSecNumber;
        this.membershipStartDate = LocalDate.parse(membershipStartDate);
        this.membershipRenewDate = LocalDate.parse(membershipRenewDate);
        this.membershipType = membershipType;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getSocialSecNumber() {
        return socialSecNumber;
    }

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public LocalDate getMembershipRenewDate() {
        return membershipRenewDate;
    }

    public String getMembershipType() {
        return membershipType;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", socialSecNumber='" + socialSecNumber + '\'' +
                ", membershipStartDate=" + membershipStartDate +
                ", membershipRenewDate=" + membershipRenewDate +
                ", membershipType='" + membershipType + '\'' +
                '}';
    }
}



