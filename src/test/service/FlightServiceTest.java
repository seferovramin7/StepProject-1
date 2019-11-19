package service;

import Enum.CityArrival;

import entity.Flight;
import entity.Flights;
import entity.FlightsTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FlightServiceTest {
    private Flights flights = new Flights();

    public FlightServiceTest() throws IOException {
    }

    @Test
    public void getAllFlightsPositive() {
        Assert.assertEquals(6, getExpectedResult().size());
        int count = 0;
        for (Object flight : getExpectedResult()) {
            Assert.assertEquals(getExpectedResult().get(count++).toString(), flight.toString());
        }
    }

    @Test
    public void getAllFlightsNegative() {
        Assert.assertNotEquals(7, getExpectedResult().size());
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
        Assert.assertEquals(getExpectedResult().get(0).toString(), flights.getFlightsByDate("06.11.2019").get(0).toString());
    }

    @Test
    public void getFlightsByDateNegative() {
        Assert.assertNotEquals(getExpectedResult().get(1).toString(), flights.getFlightsByDate("07.10.2019").get(0).toString());
    }

    @Test
    public void getFlightByArrivalCityAndDateAndShowPositive() {
        Assert.assertEquals(getExpectedResult().get(0).toString(), flights.getFlightsByArrival(CityArrival.BAKU).get(0).toString());
        Assert.assertEquals(getExpectedResult().get(0).toString(), flights.getFlightsByDate("06.11.2019").get(0).toString());
    }

    @Test
    public void getFlightByArrivalCityAndDateAndShowNegative() {
        Assert.assertNotEquals(getExpectedResult().get(0).toString(), flights.getFlightsByArrival(CityArrival.BAKU).get(1).toString());
        Assert.assertNotEquals(getExpectedResult().get(1).toString(), flights.getFlightsByDate("07.10.2019").get(0).toString());
    }

    @Test
    public void getFlightByFlightNumberPositive() {
        Assert.assertEquals(getExpectedResult().get(0).toString(), flights.getFlightByFlightNumber("I18").get(0).toString());
    }

    @Test
    public void getFlightByFlightNumberNegative() {
        Assert.assertNotEquals(getExpectedResult().get(1).toString(), flights.getFlightByFlightNumber("I18").get(0).toString());
    }

    @Test
    public void showFlightsBy24HoursPositive() {
        Assert.assertEquals(getExpectedResult().get(1), flights.getFlightsBy24Hours().get(6));
    }

    @Test
    public void showFlightsBy24HoursNegative() {
        Assert.assertNotEquals(getExpectedResult().get(0), flights.getFlightsBy24Hours().get(0));
    }

    public List<Flight> getExpectedResult() {
        FlightsTest flightsTest = null;
        try {
            flightsTest = new FlightsTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert flightsTest != null;
        return flightsTest.createNewListFlights();
    }
}
