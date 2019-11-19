package entity;

import Enum.CityArrival;
import dao.FlightsDAO;
import io.DriverFiles;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Flights implements FlightsDAO {

    private List<Flight> flights = new ArrayList<>();
    private SimpleDateFormat dateTime = new SimpleDateFormat("dd.MM.yyyy");

    public Flights(List<Flight> list) {
        this.flights = list;
    }
    public Flights() throws IOException {
        String[] fl = DriverFiles.read("flights.txt").split("\n");
        for (String s:fl) {
            Flight flight = new Flight(s);
            flights.add(flight);
        }
    }
    @Override
    public List<Flight> getFlightsByArrival(CityArrival cityArrival){
        return this.flights.stream()
                .filter(flight -> flight.getArrival() == cityArrival)
                .collect(Collectors.toList());
    }
    @Override
    public List<Flight> getFlightsByDate(String date){
        List<Flight> listRoutsByDate = new ArrayList<>();
        flights.stream()
                .filter(flight -> dateTime.format(flight.getDate()).equals(date))
                .forEach(listRoutsByDate::add);
        return listRoutsByDate;
    }
    @Override
    public List<Flight> getFlightByFlightNumber(String flightNumber) {
        List<Flight> flights = new ArrayList<>();
        this.flights.stream()
                .filter(flight -> flight.getFlightRoute().equalsIgnoreCase(flightNumber))
                .forEach(flights::add);
        return flights;
    }
    @Override
    public List<Flight> getFlightsBy24Hours() {
        List<Flight> lisOfFlightBy24Hours = new ArrayList<>();
        long currentT=new Date().getTime();
        long addedCurrent = currentT + 24 * 60 * 60 * 1000;
        flights.stream().filter(flight -> flight.getDate().getTime() <= addedCurrent&& flight.getDate().getTime()>=currentT)
                .sorted(Comparator.comparing(flight -> flight.getDate().getTime()))
                .forEach(lisOfFlightBy24Hours::add);
        return lisOfFlightBy24Hours;
    }

    @Override
    public String toString() {
        return flights.toString().replaceAll("^\\[|]$", "").replaceAll(",", "") ;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}