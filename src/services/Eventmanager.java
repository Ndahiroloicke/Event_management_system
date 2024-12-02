package services;

import Models.*;
import utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Eventmanager {
    private List<Event> events = new ArrayList<>(); // In-memory cache of events

    // Add event to the database
    public void addEvent(Event event) throws SQLException {
        String query = "INSERT INTO events(name, date, location, tickets_available, type, additional_info) VALUES (?,?,?,?,?,?)";
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, event.getName());
            statement.setDate(2, Date.valueOf(event.getDate()));
            statement.setString(3, event.getLocation());
            statement.setInt(4, event.getTicketsAvailable());
            statement.setString(5, event instanceof Conference ? "Conference" : "Concert");
            statement.setString(6, event instanceof Conference ? ((Conference) event).getSpeaker() : ((Concert) event).getArtist());

            statement.executeUpdate();
            System.out.println("Event added to database successfully");

            refreshEvents(); // Refresh events cache after addition
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display all events
    public void displayAllEvents() {
        refreshEvents(); // Ensure events cache is updated

        if (events.isEmpty()) {
            System.out.println("No Events Available.");
        } else {
            for (Event event : events) {
                event.displaDetails();
                System.out.println("--------------------");
            }
        }
    }

    // Cancel a ticket
    public void cancelTicket(int eventId) {
        String query = "UPDATE events SET tickets_available = tickets_available - 1 WHERE id = ?";

        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            // Set the event ID
            statement.setInt(1, eventId);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Ticket cancelled successfully.");
            } else {
                System.out.println("Invalid event ID or the event does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Delete an event
    public void deleteEvent(int eventId) {
        String query = "DELETE FROM events WHERE id = ?";

        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            // Set the event ID
            statement.setInt(1, eventId);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Event deleted successfully.");
            } else {
                System.out.println("Invalid event ID or the event does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateEvent(int eventId) {
        String query = "UPDATE events SET name = ?, date = ?, location = ?, tickets_available = ?, type = ?, additional_info = ? WHERE id = ?";

        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            Scanner scanner = new Scanner(System.in);

            // Prompt user for new event details
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.print("Enter new location: ");
            String location = scanner.nextLine();
            System.out.print("Enter new tickets available: ");
            int ticketsAvailable = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            System.out.print("Enter new type (Conference/Concert): ");
            String type = scanner.nextLine();
            System.out.print("Enter new additional info (Speaker/Artist): ");
            String additionalInfo = scanner.nextLine();

            // Set parameters and execute update query
            statement.setString(1, name);
            statement.setString(2, date);
            statement.setString(3, location);
            statement.setInt(4, ticketsAvailable);
            statement.setString(5, type);
            statement.setString(6, additionalInfo);
            statement.setInt(7, eventId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Event updated successfully.");
            } else {
                System.out.println("Event ID not found or failed to update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Book a ticket
    public void booktickets(int eventId) {
        String query = "UPDATE events SET tickets_available = tickets_available - 1 WHERE id = ? AND tickets_available > 0";
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, eventId);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Ticket Booked Successfully");
                refreshEvents(); // Refresh events cache after booking
            } else {
                System.out.println("No Tickets Available or Invalid event ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Refresh the events cache from the database
    public void refreshEvents() {
        String query = "SELECT * FROM events";
        try (
                Connection connection = db.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            events.clear(); // Clear current in-memory events list

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = resultSet.getString("date");
                String location = resultSet.getString("location");
                int ticketsAvailable = resultSet.getInt("tickets_available");
                String type = resultSet.getString("type");
                String additionalInfo = resultSet.getString("additional_info");

                if ("Conference".equals(type)) {
                    events.add(new Conference(id, name, date, location, ticketsAvailable, additionalInfo));
                } else {
                    events.add(new Concert(id, name, date, location, ticketsAvailable, additionalInfo));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
