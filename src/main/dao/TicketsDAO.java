package dao;

import entity.Flight;
import entity.Ticket;

import java.util.List;

public interface TicketsDAO {
    List<Ticket> AllTickets();
    Ticket TicketById(int id);
    void createNewTicket(Flight flight, String name, String surname);
    void userTickets(String name, String surname);
    void removeTicket(int id);
}
