package controller;

import entity.Flight;
import org.junit.Assert;
import org.junit.Test;
import service.FlightServiceTest;

import java.io.IOException;
import java.util.List;

public class ControllerTest {

    @Test
    public void removeTicketPositive() throws IOException {
        FlightServiceTest flightServiceTest = new FlightServiceTest();
        List<Flight> expectedResult = flightServiceTest.getExpectedResult();
        Flight flight = expectedResult.get(3);
        expectedResult.remove(3);
        flight.setFreeSeats(flight.getFreeSeats() + 1);

        Assert.assertEquals(flight.getFreeSeats(), flightServiceTest.getExpectedResult().get(3).getFreeSeats() + 1);
    }

    @Test
    public void removeTicketNegative() throws IOException {
        FlightServiceTest flightServiceTest = new FlightServiceTest();
        List<Flight> expectedResult = flightServiceTest.getExpectedResult();
        Flight flight = expectedResult.get(3);
        expectedResult.remove(3);
        flight.setFreeSeats(flight.getFreeSeats() + 1);

        Assert.assertNotEquals(flight.getFreeSeats(), flightServiceTest.getExpectedResult().get(3).getFreeSeats());
    }
}
