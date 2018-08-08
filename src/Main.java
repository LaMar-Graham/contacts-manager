import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    private static Scanner in = new Scanner(System.in);
    private static File file = new File("Address.txt");
    static List<Person> people = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readPeopleFromFile();
        showMainMenu();
    }

    private static void findPerson() throws IOException {
        System.out.println("1. Find by First name");
        System.out.println("2. Find by Last name");

        String choice;
        do{
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    findByFirstName();
                    break;
                case "2":
                    findByLastName();
                    break;
                default:
                    System.out.println("Choose 1 or 2: ");
            }
        } while (!choice.equals("1") && !choice.equals("2"));
        System.out.println();
        showMainMenu();
    }

    private static void findByFirstName(){
        System.out.println("Enter First Name:");
        String firstNameToFind = in.nextLine();
        int matches = 0;
        for(Person person : people) {
            if(person.getFirstName().equals(firstNameToFind)) {
                System.out.println(person);
                matches++;
            }
        }
        if(matches<=0) {
            System.out.println("There is no person with this first name");
        }
    }

    private static void findByLastName(){
        System.out.println("Enter Last Name:");
        String lastNameToFind = in.nextLine();
        int matches = 0;
        for(Person person : people) {
            if(person.getLastName().equals(lastNameToFind)) {
                System.out.println(person);
                matches++;
            }
        }
        if(matches<=0) {
            System.out.println("There is no person with this last name");
        }
    }

    private static void addPerson() throws IOException {
        System.out.println("Enter First Name: ");
        String firstName = in.nextLine();
        System.out.println("Enter Last Name: ");
        String lastName = in.nextLine();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = in.nextLine();
        System.out.println("Enter Email: ");
        String email = in.nextLine();

        Person person = new Person(firstName, lastName, phoneNumber, email);
        addToFile(person);
        people.add(person);
        System.out.println();
        System.out.println("Added person number: " + person.id + person);
        System.out.println();
        showMainMenu();
    }

    private static void  addToFile(Person person) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("\n" + person.getFirstName()+ " " + person.getLastName() + " | " + person.getPhoneNumber() + " | " + person.getEmail());
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    private static boolean readPeopleFromFile() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String name = null;
            while((name = reader.readLine()) != null) {
                Person person = new Person(name, reader.readLine(), reader.readLine(), reader.readLine());
                people.add(person);
                reader.readLine();
            }
            return true;
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    private static void showMainMenu() throws IOException {
        System.out.println("1. Add Person");
        System.out.println("2. Find Person");
        System.out.println("3. Show all contacts");
        System.out.println("4. Close program");

        String choice;
        do {
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    addPerson();
                    break;
                case "2":
                    findPerson();
                    break;
                case "3":
                    System.out.println(people);
                    System.out.println();
                    showMainMenu();
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter number from 1 to 4");
            }
        }while (!choice.equals("4"));
    }

}