
import Models.*;
import services.*;

import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Eventmanager eventmanager = new Eventmanager();

        while (true){
            System.out.println("\nEvent Management System");
            System.out.println("1. Add Conference");
            System.out.println("2. Add Concert");
            System.out.println("3. Display All Events");
            System.out.println("4. Book Ticket");
            System.out.println("5. Exit");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.println("Enter Conference Details:");
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Date(YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Tickets Available: ");
                    int tickets = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Speaker: ");
                    String speaker = scanner.nextLine();

                    eventmanager.addEvent(new Conference(id, name, date, location, tickets, speaker));
                }
                case 2 -> {
                    System.out.println("Enter Concert Details:");
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Date(YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Tickets Available: ");
                    int tickets = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Artist: ");
                    String artist = scanner.nextLine();

                    eventmanager.addEvent(new Concert(id, name, date, location, tickets, artist));
                }

                case 3 -> eventmanager.displayAllEvents();

                case 4 -> {
                    System.out.print("Enter Event ID to book a ticket: ");
                    int eventId = scanner.nextInt();
                    eventmanager.booktickets(eventId);
                }

                case 5 -> {
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid option. Please try again.");
            }

        }


    }
}