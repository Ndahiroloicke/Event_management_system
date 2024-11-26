package services;
import Models.*;
import utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Eventmanager {

    public void addEvent(Event event) throws SQLException {
        String query = "INSERT INTO events(name, date, location, tickets_available, type, additional_info) VALUES (?,?,?,?,?,?)";
        try( Connection connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1,event.getName());
            statement.setDate(2,Date.valueOf(event.getDate()));
            statement.setString(3,event.getLocation());
            statement.setInt(4,event.getTicketsAvailable());
            statement.setString(5, event instanceof Conference ? "Conference" : "Concert");
            statement.setString(6, event instanceof Conference ? ((Conference) event).getSpeaker() : ((Concert) event).getArtist());

            statement.executeUpdate();
            System.out.println("Event added to database successfully");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void displayAllEvents() {
        String query2 = "Select * FROM events";
        try(Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query2);) {

            List<Event> events = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String Date = resultSet.getString("date").toString();
                String Location = resultSet.getString("location");
                int ticketsAvailable = resultSet.getInt("tickets_available");
                String type = resultSet.getString("type");
                String additionalinfo = resultSet.getString("additional_info");

                if("Conference".equals(type)){
                    events.add(new Conference(id, name, Date, Location, ticketsAvailable, additionalinfo));
                }else {
                    events.add(new Concert(id, name, Date, Location, ticketsAvailable, additionalinfo));
                }
            }

            if (events.isEmpty()){
                System.out.println("No Events Available.");
            }else{
                for (Event event: events){
                    event.displaDetails();
                    System.out.println("--------------------");
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
    }
    public void booktickets (int eventId) {
        String query = "UPDATE events SET tickets_available = tickets_available - 1  WHERE  id = ? AND tickets_available > 0 ";
        try(
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
                ) {
            statement.setInt(1,eventId);
            int rowsUpdated = statement.executeUpdate();

            if(rowsUpdated > 0 ){
                System.out.println("Ticket Booked Successfully");
            }else {
                System.out.println("No Tickets Available or Invalid event ID");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
