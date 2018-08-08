public class Contact {
    public static void main(String[] args) {

    }

    private String firstName;
    private String lastName;
    private String number;
    private String contact;

    public Contact(String firstName, String lastName, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.contact = firstName + " " + lastName + " |" + " " + number;
    }

    public String getContact() {
        return contact;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getNumber() {
        return number;
    }

    public String getLastName() {
        return lastName;
    }
}