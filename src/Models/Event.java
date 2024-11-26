package Models;

public abstract class Event {
    private int id;
    private String name;
    private String date;
    private String location;
    private int ticketsAvailable;

    public  Event(int id, String name, String date, String location, int ticketsAvailable){
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.ticketsAvailable = ticketsAvailable;
    }

    public int getId(){return id;};
    public String getName() {return name;};
    public String getDate() {return date;};
    public String getLocation() {return location;};
    public int getTicketsAvailable(){return  ticketsAvailable;};
    public void setTicketsAvailable(int ticketsAvailable) {this.ticketsAvailable = ticketsAvailable;};

    public abstract void displaDetails();
}
