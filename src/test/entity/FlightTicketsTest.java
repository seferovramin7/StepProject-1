package entity;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightTicketsTest {

    public List<Ticket> getExpectedResult() {
        return createListTicket();
    }

    public List<Ticket> createListTicket() {
        List<Ticket> ticketList = new ArrayList<>();

        ticketList.add(createTicket("Ramin", "Safarov", 0));
        ticketList.add(createTicket("Elvin", "Ismayilov", 26));
        ticketList.add(createTicket("Gurban", "Esedov", 57));
        ticketList.add(createTicket("Alex", "Rykhalsky", 84));

        return ticketList;
    }

    public Ticket createTicket(String name, String surname, int flightIndex) {
        Flights flights = null;
        try {
            flights = new Flights();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert flights != null;
        return new Ticket(flights.getFlights().get(flightIndex), new User(name, surname));
    }

    public FlightTicketsTest() throws IOException {
    }

    @Test
    public void getAllTicketsPositive() {
        Assert.assertEquals(getExpectedResult(), createListTicket());
    }

    @Test
    public void getAllTicketsNegative() {
        Assert.assertNotEquals(getExpectedResult(), createListTicket().add(createTicket("Aga", "Dadasov", 0)));
    }

    @Test
    public void getTicketByIDPositive() {
        Ticket ticket = createListTicket().get(0);
        ticket.setTicketId(1415548977);
        Assert.assertEquals(ticket.getTicketId(), 1415548977);
    }

    @Test
    public void getTicketByIDNegative() {
        Ticket ticket = createListTicket().get(0);
        Assert.assertNotEquals(ticket.getTicketId(), 1415548988);
    }

    @Test
    public void createTicketPositive() throws IOException {
        Flights flights = new Flights();
        Ticket ticket = new Ticket(flights.getFlights().get(0), new User("Heyder", "Ismetov"));

        List<Ticket> listTicket = createListTicket();
        listTicket.add(ticket);

        Assert.assertEquals((getExpectedResult().toString().replaceAll("^\\[]|]$", ", ") + ticket.toString() + "]"), listTicket.toString());
    }

    @Test
    public void createTicketNegative() throws IOException {
        Flights flights = new Flights();
        Ticket ticket = new Ticket(flights.getFlights().get(0), new User("Heyder", "Ismetov"));

        List<Ticket> listTicket = createListTicket();

        Assert.assertNotEquals((getExpectedResult().toString().replaceAll("^\\[]|]$", ", ") + ticket.toString() + "]"), listTicket.toString());

    }

    @Test
    public void showUserTicketsPositive() {
        Ticket ticket = getExpectedResult().get(0);
        Assert.assertEquals(ticket.getUser().toString(), ("Ramin " + "Safarov").toUpperCase());
    }

    @Test
    public void showUserTicketsNegative() {
        Ticket ticket = getExpectedResult().get(0);
        Assert.assertNotEquals(ticket.getUser().toString(), ("Humbet " + "Esedov").toUpperCase());
    }

    @Test
    public void removeTicketPositive() {
        List<Ticket> expectedResult = getExpectedResult();
        expectedResult.remove(2);
        List<Ticket> removeListTicket = createListTicket();
        removeListTicket.remove(2);
        Assert.assertEquals(getExpectedResult().remove(2), createListTicket().remove(2));
        Assert.assertEquals(expectedResult, removeListTicket);
    }

    @Test
    public void removeTicketNegative() {
        List<Ticket> expectedResult = getExpectedResult();
        expectedResult.remove(3);
        List<Ticket> removeListTicket = createListTicket();
        removeListTicket.remove(2);
        Assert.assertNotEquals(getExpectedResult().remove(3), createListTicket().remove(2));
        Assert.assertNotEquals(expectedResult, removeListTicket);
    }
}
