import util.Input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.Scanner;

public class ContactsMain {
    public static Input userInput = new Input();
    public static ArrayList<Contact> contacts = new ArrayList<>();
    public static String directory = "data";
    public static String filename = "contacts.txt";

    public static void main(String[] args) {

        try {
            readLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createFileIfNotExists(directory, filename);


        showMenu();

    }


    public static void showMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. View all Contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit");
        System.out.print("Enter an option(1, 2, 3, 4, 5):");
        int choice = scan.nextInt();
        System.out.println();
        switch (choice) {
            case 1:
                System.out.println("Name:        ||    PhoneNumber:");
                System.out.println("===============================");
                viewAllContacts();
                System.out.println("\n");
                showMenu();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchArray();
                showMenu();
                break;
            case 4:
                viewAllContacts();
                deleteContact();
                break;
            case 5:
                System.out.println("Thank you for using Contact Manager 1.0");
                try {
                    writeListToFile(contacts, directory, filename);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.exit(0);
                break;
            default:
                System.out.println("Not a valid choice");
                showMenu();
        }
    }

    public static void viewAllContacts() {

        try {
            for (Contact contact : contacts) {
                System.out.println(contact.getContact());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Contact addNewContact() {

        System.out.print("Please enter contacts first name: ");
        String firstName = userInput.getString();
        System.out.print("Last name: ");
        String lastName = userInput.getString();
        System.out.print("Please enter contact number: ");
        String number = userInput.getString();
        Contact newContact = new Contact(firstName, lastName, number);
        contacts.add(newContact);
        try {
            writeListToFile(contacts, directory, filename);
        } catch (Exception e) {
            System.out.println(e);
        }
        again();

        return newContact;
    }

    public static void again() {
        System.out.println("Would you like to add another? ");
        System.out.print("(yes or no): ");
        boolean answer = userInput.yesNo();
        if (answer) {
            addNewContact();
        } else {
            showMenu();
        }
    }

    public static void createFileIfNotExists(String directory, String filename) {

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if (Files.notExists(dataFile)) {
                Files.createFile(dataFile);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeListToFile(ArrayList<Contact> contacts, String directory, String filename) throws IOException {
        ArrayList<String> out = new ArrayList<>();
        for (Contact contact : contacts) {
            out.add(contact.getContact());
        }
        Path filepath = Paths.get(directory, filename);
        Files.write(filepath, out);

    }

    public static void readLines() throws IOException {


        Scanner txtInput = new Scanner(new File("data/contacts.txt"));
        while (txtInput.hasNext()) {
            Contact contact = new Contact(txtInput.next(), txtInput.next(), txtInput.next());
            contacts.add(contact);
        }
        txtInput.close();

    }

    public static void searchArray() {
        System.out.print("Who would you like to search for: ");
        String search = userInput.getString();


        boolean searching = true;
        String info = "";
        while (searching) {
            int counter = 0;
            for (Contact contact : contacts) {
                String firstContactString = contact.getFirstName();
                String lastContactString = contact.getLastName();
                String contactInfo = contact.getContact();
                if (firstContactString.toLowerCase().contains(search.toLowerCase())) {
                    System.out.println();
                    info = contactInfo;
                    searching = false;
                } else if (lastContactString.toLowerCase().contains(search.toLowerCase())) {
                    System.out.println();
                    info = contactInfo;
                    searching = false;
                } else {
                    counter += 1;
                    if (counter == contacts.size()) {
                        System.out.println("That name wasn't found.");
                        System.out.println();
                        searching = false;
                    }
                }
            }

        }
        System.out.println(info);


    }

    public static void deleteContact() {
        String search = userInput.getString("Which contact do you want to delete");
        int delete = 0;
        for (Contact contact : contacts) {
            String compareString = contact.getFirstName();
            if (compareString.toLowerCase().contains(search.toLowerCase())) {
                delete = contacts.indexOf(contact);
            }
        }
        contacts.remove(delete);
        showMenu();

    }
}
