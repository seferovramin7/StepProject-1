package entity;

import Enum.CityArrival;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightsTest {

    private Flights flights = new Flights();

    public FlightsTest() throws IOException {
    }

    public List<Flight> getExpectedResult() {
        return createNewListFlights();
    }

    @Test
    public void getFlightsByArrivalPositive() {
        Assert.assertEquals(getExpectedResult().get(0).toString(), flights.getFlightsByArrival(CityArrival.BAKU).get(0).toString());
    }

    @Test
    public void getFlightsByArrivalNegative() {
        Assert.assertNotEquals(getExpectedResult().get(0).toString(), flights.getFlightsByArrival(CityArrival.BAKU).get(1).toString());
    }

    @Test
    public void getFlightsByDatePositive() {
        Assert.assertEquals(getExpectedResult().get(0).toString(), flights.getFlightsByDate("06.02.2019").get(0).toString());
    }

    @Test
    public void getFlightsByDateNegative() {
        Assert.assertNotEquals(getExpectedResult().get(1).toString(), flights.getFlightsByDate("07.02.2019").get(0).toString());
    }

    @Test
    public void getFlightByFlightNumberPositive() {
        Assert.assertEquals(getExpectedResult().get(0).toString(), flights.getFlightByFlightNumber("KM-101").get(0).toString());
    }

    @Test
    public void getFlightByFlightNumberNegative() {
        Assert.assertNotEquals(getExpectedResult().get(1).toString(), flights.getFlightByFlightNumber("KM-101").get(0).toString());
    }

    @Test
    public void getFlightsBy24HoursPositive() {
        Assert.assertEquals(getExpectedResult().get(1), flights.getFlightsBy24Hours().get(4));
    }

    @Test
    public void getFlightsBy24HoursNegative() {
        Assert.assertNotEquals(getExpectedResult().get(0), flights.getFlightsBy24Hours().get(0));
    }

    public List<Flight> createNewListFlights() {
        List<Flight> flightList = new ArrayList<>();

        flightList.add(flights.getFlights().get(0));
        flightList.add(flights.getFlights().get(26));
        flightList.add(flights.getFlights().get(57));
        flightList.add(flights.getFlights().get(84));
        flightList.add(flights.getFlights().get(106));
        flightList.add(flights.getFlights().get(131));

        return flightList;
    }
}
