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

    public Person(String firstName, String lastName,String address, String email, String socialSecNumber, LocalDate membershipStartDate, LocalDate membershipRenewDate, String membershipType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address =address;
        this.email = email;
        this.socialSecNumber = socialSecNumber;
        this.membershipStartDate = membershipStartDate;
        this.membershipRenewDate = membershipRenewDate;
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
}



