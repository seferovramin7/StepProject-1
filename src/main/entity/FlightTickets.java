package entity;

import dao.TicketsDAO;
import io.DriverFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightTickets implements TicketsDAO {

    private List<Ticket> tickets = new ArrayList<>();
    private String printInfo = "---------------------------------------------------------------------------------------------------------\n" +
            "#\t" + " Full name:\t\t\t" + "TicketID:\t\t" + "Flight№:\t" + "From:\t\t" + "To:\t\t\t" + "Date:\t\t" + "Time:\t" + "Duration:\t\n" +
            "---------------------------------------------------------------------------------------------------------\n" +
            tickets.toString().replaceAll("^\\[|\\]$", "").replaceAll("\\,", "");

    public FlightTickets() {
        File file = new File("tickets.txt");
        if (!file.exists() || file.length() == 0) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String[] tk = new String[0];
            try {
                tk = DriverFiles.read("tickets.txt").split("\n");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (String s : tk) {
                Ticket ticket = new Ticket(s);
                tickets.add(ticket);
            }
        }
    }

    public FlightTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public List<Ticket> AllTickets() {
        return tickets;
    }

    @Override
    public Ticket TicketById(int id) {
        return tickets.stream().filter(ticket -> ticket.getTicketId() == id).findFirst().get();
    }

    @Override
    public void createNewTicket(Flight flight, String name, String surname) {
        User user = new User(name, surname);
        Ticket ticket1 = new Ticket(flight, user);
        if (!tickets.contains(ticket1)) {
            tickets.add(ticket1);
            flight.setFreeSeats(flight.getFreeSeats() - 1);

        } else {
            System.out.println("YOU CANT BOOK A LOT OF TICKETS ON ONE FLIGHT FOR ONE PASSENGER");
        }
    }
    @Override
    public void userTickets(String name, String surname) {
        System.out.println(printInfo);
        tickets.stream()
                .filter(ticket -> ticket.getUser().getSurname().equals(surname) && ticket.getUser().getName().equals(name))
                .forEach(ticket -> System.out.println(String.format("%-5d", AllTickets().indexOf(ticket) + 1) + ticket));
        System.out.println("\n---------------------------------------------------------------------------------------------------------");
    }
    @Override
    public void removeTicket(int id) {

        tickets.remove(TicketById(id));

    }

    @Override
    public String toString() {
        return "";
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
