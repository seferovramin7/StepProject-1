package service;

import entity.Flights;
import entity.Ticket;
import entity.FlightTicketsTest;
import entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TicketServiceTest {

    public List<Ticket> getExpectedResult() {
        FlightTicketsTest ticketsTest = null;
        try {
            ticketsTest = new FlightTicketsTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert ticketsTest != null;
        return ticketsTest.createListTicket();
    }

    private FlightTicketsTest ticketsTest;
    {
        try {
            ticketsTest = new FlightTicketsTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllTicketsPositive() {
        Assert.assertEquals(ticketsTest.createListTicket(), getExpectedResult());
    }

    @Test
    public void getAllTicketsNegative() {
        Assert.assertNotEquals(getExpectedResult(), ticketsTest.createListTicket().add(ticketsTest.createTicket("Ramin", "Safarov", 0)));
    }

    @Test
    public void getTicketByIDPositive() {
        Ticket ticket = ticketsTest.createListTicket().get(0);
        ticket.setTicketId(1415548977);
        Assert.assertEquals(ticket.getTicketId(), 1415548977);
    }

    @Test
    public void getTicketByIDNegative() {
        Ticket ticket = ticketsTest.createListTicket().get(0);
        Assert.assertNotEquals(ticket.getTicketId(), 1415548988);
    }

    @Test
    public void createTicketPositive() throws IOException {
        Flights flights = new Flights();
        Ticket ticket = new Ticket(flights.getFlights().get(0), new User("Jale", "Esedov"));

        List<Ticket> listTicket = ticketsTest.createListTicket();
        listTicket.add(ticket);

        Assert.assertEquals((getExpectedResult().toString().replaceAll("^\\[]|]$", ", ") + ticket.toString() + "]"), listTicket.toString());
    }

    @Test
    public void createTicketNegative() throws IOException {
        Flights flights = new Flights();
        Ticket ticket = new Ticket(flights.getFlights().get(0), new User("Jale", "Esedov"));

        List<Ticket> listTicket = ticketsTest.createListTicket();

        Assert.assertNotEquals((getExpectedResult().toString().replaceAll("^\\[]|]$", ", ") + ticket.toString() + "]"), listTicket.toString());
    }

    @Test
    public void showUserTicketsPositive() {
        Ticket ticket = getExpectedResult().get(0);
        Assert.assertEquals(ticket.getUser().toString(), ("Vladimir " + "Safarov").toUpperCase());
    }

    @Test
    public void showUserTicketsNegative() {
        Ticket ticket = getExpectedResult().get(0);
        Assert.assertNotEquals(ticket.getUser().toString(), ("Seyyare " + "Qasimova").toUpperCase());
    }

    @Test
    public void removeTicketPositive() {
        List<Ticket> expectedResult = getExpectedResult();
        expectedResult.remove(3);
        List<Ticket> removeListTicket = ticketsTest.createListTicket();
        removeListTicket.remove(3);
        Assert.assertEquals(getExpectedResult().remove(3), ticketsTest.createListTicket().remove(3));
        Assert.assertEquals(expectedResult, removeListTicket);
    }

    @Test
    public void removeTicketNegative() {
        List<Ticket> expectedResult = getExpectedResult();
        expectedResult.remove(1);
        List<Ticket> removeListTicket = ticketsTest.createListTicket();
        removeListTicket.remove(2);
        Assert.assertNotEquals(getExpectedResult().remove(1), ticketsTest.createListTicket().remove(2));
        Assert.assertNotEquals(expectedResult, removeListTicket);
    }
}
