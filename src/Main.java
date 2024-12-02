import Models.*;
import services.*;

import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Eventmanager eventmanager = new Eventmanager();

        while (true) {
            System.out.println("\nEvent Management System");
            System.out.println("1. Add Conference");
            System.out.println("2. Add Concert");
            System.out.println("3. Display All Events");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Delete Event");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine()); // Use nextLine to avoid newline issues

                switch (choice) {

                    case 1 -> {
                        System.out.println("Enter Conference Details:");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        System.out.print("Location: ");
                        String location = scanner.nextLine();
                        System.out.print("Tickets Available: ");
                        int tickets = Integer.parseInt(scanner.nextLine());
                        System.out.print("Speaker: ");
                        String speaker = scanner.nextLine();

                        eventmanager.addEvent(new Conference(0, name, date, location, tickets, speaker));
                    }
                    case 2 -> {
                        System.out.println("Enter Concert Details:");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        System.out.print("Location: ");
                        String location = scanner.nextLine();
                        System.out.print("Tickets Available: ");
                        int tickets = Integer.parseInt(scanner.nextLine());
                        System.out.print("Artist: ");
                        String artist = scanner.nextLine();

                        eventmanager.addEvent(new Concert(0, name, date, location, tickets, artist));
                    }
                    case 3 -> eventmanager.displayAllEvents();

                    case 4 -> {
                        System.out.print("Enter Event ID to book a ticket: ");
                        int eventId = Integer.parseInt(scanner.nextLine());
                        eventmanager.booktickets(eventId);
                    }

                    case 5 -> {
                        System.out.print("Enter Event ID to cancel a ticket: ");
                        int eventId = Integer.parseInt(scanner.nextLine());
                        eventmanager.cancelTicket(eventId); // This cancels a ticket, reducing the count
                    }

                    case 6 -> {
                        System.out.print("Enter Event ID to delete the event: ");
                        int eventId = Integer.parseInt(scanner.nextLine());
                        eventmanager.deleteEvent(eventId); // This deletes the entire event
                    }

                    case 7 -> {
                        System.out.println("Exiting system. Goodbye!");
                        scanner.close();
                        return;
                    }

                    default -> System.out.println("Invalid option. Please choose a valid menu option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (SQLException e) {
                System.out.println("An error occurred: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}
