public class Person {
    static int id = Main.people.size();
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    Person(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        id++;
    }

    String getFirstName() { return firstName; }

    String getLastName() { return lastName; }

    String getPhoneNumber() { return phoneNumber; }

    String getEmail() { return email; }

    @Override
    public String toString() {
        return "\n\nFirst Name: " + getFirstName() + "\nLast Name: " + getLastName() + "\nPhone number: " + getPhoneNumber() + "\nEmail: " + getEmail();
    }
}