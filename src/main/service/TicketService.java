package service;

import dao.TicketsDAO;
import entity.Flight;
import entity.Ticket;
import entity.FlightTickets;

import java.util.List;

public class TicketService {
    private TicketsDAO ticketsDAO = new FlightTickets();

    private String printInfo = "---------------------------------------------------------------------------------------------------------\n" +
            "#\t" + " Full name:\t\t\t" + "TicketID:\t\t" + "Flight№:\t" + "From:\t\t" + "To:\t\t\t" + "Date:\t\t" + "Time:\t" + "Duration:\t\n" +
            "---------------------------------------------------------------------------------------------------------\n" +
            ticketsDAO.toString().replaceAll("^\\[|\\]$", "").replaceAll("\\,", "");


    public List<Ticket> getAllTickets() {
        return ticketsDAO.AllTickets();
    }

    public TicketService() {
    }

    public void displayAllTicket() {
        System.out.println(printInfo);
        if (getAllTickets() != null || getAllTickets().size() != 0) {
            getAllTickets().forEach(ticket -> System.out.println(String.format("%-5d", getAllTickets().indexOf(ticket) + 1) + ticket));
        } else {
            System.out.println("No tickets presented");
        }
        System.out.println("\n---------------------------------------------------------------------------------------------------------");
    }


    public Ticket getTicketById(int id) {
        return ticketsDAO.TicketById(id);
    }

    public void createTicket(Flight flight, String name, String surname) {
        ticketsDAO.createNewTicket(flight, name, surname);
    }

    public void showUserTickets(String name, String surname) {
        ticketsDAO.userTickets(name, surname);
    }

    public void removeTicket(int id) {
        ticketsDAO.removeTicket(id);
    }

    public TicketService(TicketsDAO ticketsDAO) {
        this.ticketsDAO = ticketsDAO;
    }
}
