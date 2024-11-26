package Models;

public class Concert extends  Event{

    private String artist;

    public Concert(int id, String name, String date, String location, int ticketsAvailable, String artist){
        super(id,name,date,location,ticketsAvailable);
        this.artist = artist;
    }

    public String getArtist(){return  artist;};

    @Override
    public void displaDetails() {
        System.out.println("ConcertID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Date: " + getDate());
        System.out.println("Location: " + getLocation());
        System.out.println("Tickets Available: " + getTicketsAvailable());
        System.out.println("Artist: " + getArtist());
    }
}
