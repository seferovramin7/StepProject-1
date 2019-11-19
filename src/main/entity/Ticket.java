package entity;

import java.util.Objects;

public class Ticket {

   private Flight flight;
   private int ticketId;
   private User user;

    public Ticket() {
    }
    public Ticket(String raw) {this(raw.split("\t"));
    }
    public Ticket(String []raw)
    {this(raw[0], raw[1], raw[2], raw[3], raw[4], raw[5], raw[6], raw[7], raw[8]);
    }
    public Ticket(String userName, String userSurname, String ticketId, String flightNumber, String from, String to, String date, String time, String duration){
        this.user=new User(userName,userSurname);
        this.ticketId=Integer.parseInt(ticketId);
        this.flight= new Flight(flightNumber,from,to,date,time,duration);
    }
    public Ticket(Flight flight, User user) {
        this.user= user;
        this.flight = flight;
        this.ticketId = createIdTicket();
    }
     public int createIdTicket(){
         int i = flight.hashCode()+user.hashCode();
         if (i<0){
             int y=i*(-1);
             i=y;
         }
         return i;
     }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId == ticket.ticketId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }
    public String toText(){
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", user.getName(),user.getSurname(),ticketId, flight.getFlightRoute(), flight.getDeparture(), flight.getArrival(), flight.getDateFormat().format(flight.getDate()), flight.getTimeFormat().format(flight.getDate()), flight.getDurationFormat().format(flight.getDurationTime()));
    }

    @Override
    public String toString() {
        return
                String.format("%-15s",user)+"\t"+ticketId+"\t\t"+
                flight.getFlightRoute()+"\t\t"+flight.getDeparture()+ "\t\t" +
                String.format("%-12s", flight.getArrival()) +
                flight.getDateFormat().format(flight.getDate()) + "\t" +
                flight.getTimeFormat().format(flight.getDate()) + "\t" +
                String.format("%8s",flight.getDurationFormat().format(flight.getDurationTime()));
    }


    //GET|SET
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
