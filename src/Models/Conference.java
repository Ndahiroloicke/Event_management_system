package Models;

public class Conference extends Event{

    private String speaker;

    public Conference( int id, String name, String date, String location, int ticketsAvailable, String speaker){
        super(id,name, date, location, ticketsAvailable);
        this.speaker = speaker;
    }

    public String getSpeaker(){return speaker;};

    @Override
    public void displaDetails() {
        System.out.println("ConferenceID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Date: " + getDate());
        System.out.println("Location: " + getLocation());
        System.out.println("TICKETS Available: " + getTicketsAvailable());
        System.out.println("Speaker: " + speaker );
    }
}
