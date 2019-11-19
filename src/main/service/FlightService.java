package service;

import Enum.CityArrival;
import entity.Flight;
import entity.Flights;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FlightService {
    private Flights flights;

    {
        try {
            flights = new Flights();
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    //TO SHOW FOR USER
    private String printInfo = "-----------------------------------------------------------------------------------\n" +
            "#\t" + " Flight№:\t" + "From:\t\t" + "To:\t\t\t" + "Date:\t\t" + "Time:\t" + "Duration:\t" + "Free seats:\n" +
            "-----------------------------------------------------------------------------------\n";

    //DONT USE IN COMMANDS
    public List<Flight> getAllFlights() {
        return flights.getFlights();
    }

    //SHOW ALL FLIGHTS
    public void displayAllFlights() {
        System.out.println(printInfo);
        List<Flight> allFlights = this.flights.getFlights();
        if (allFlights != null || allFlights.size() != 0) {
            allFlights
                    .forEach(flight -> System.out.println(
                            (String.format("%-5d", allFlights.indexOf(flight) + 1)) + flight));
        } else {
            System.out.println("No flights presented");
        }
        System.out.println("\n-----------------------------------------------------------------------------------");
    }

    public List<Flight> getFlightsByArrival(CityArrival cityArrival) {
        System.out.println("FLIGHTS BY ARRIVAL\n" + printInfo);
        AtomicInteger index = new AtomicInteger();
        List<Flight> flightsByArrival = flights.getFlightsByArrival(cityArrival);
        flightsByArrival.stream().forEach(flight -> System.out.println(String.format("%-5d", flightsByArrival.indexOf(flight) + 1) + flight));

        if (flightsByArrival.equals(flightsByArrival == null || flightsByArrival.size() == 0)) {
            System.out.println("No flights presented");
        }
        System.out.println("\n-----------------------------------------------------------------------------------");
        return flightsByArrival;
    }

    public List<Flight> getFlightByDate(String date) {
        System.out.println("FLIGHTS BY " + date + "\n" + printInfo);
        List<Flight> flightsByDate = flights.getFlightsByDate(date);
        flightsByDate.stream().forEach(flight -> System.out.println(String.format("%-5d", flightsByDate.indexOf(flight) + 1) + flight));
        if (flightsByDate.equals(flightsByDate.size() == 0)) {
            System.out.println("No flights presented");
        }
        System.out.println("\n-----------------------------------------------------------------------------------");
        return flightsByDate;
    }

    public List<Flight> getFlightByArrivalCityAndDateAndShow(CityArrival cityArrival, String date, int numberOfPassengers) {
        System.out.println();
        System.out.println("CHOOSE YOUR FLIGHT\n" + printInfo);
        List<Flight> flightsByArrival = flights.getFlightsByArrival(cityArrival);
        List<Flight> listToShow = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        flightsByArrival.stream()
                .filter(flight -> flight.getArrival().equals(cityArrival) &&
                        dateFormat.format(flight.getDate()).equals(date) &&
                        flight.getFreeSeats() >= numberOfPassengers)
                .forEach(listToShow::add);
        if (flightsByArrival == null || flightsByArrival.size() == 0 || listToShow == null || listToShow.size() == 0) {
            System.out.println("No flights presented");
        }
        listToShow.forEach(flight -> System.out.println(String.format("%-5d", listToShow.indexOf(flight) + 1) + flight));
        System.out.println("\n-----------------------------------------------------------------------------------");
        return listToShow;
    }

    public List<Flight> getFligthByFlightNumber(String flightNumber) {
        System.out.println("\nFLIGHTS BY " + flightNumber + "\n" + printInfo);
        List<Flight> flightByFlightNumber = flights.getFlightByFlightNumber(flightNumber);
        flightByFlightNumber.stream().forEach(flight -> System.out.println(String.format("%-5d", flightByFlightNumber.indexOf(flight) + 1) + flight));
        if (flightByFlightNumber == null || flightByFlightNumber.size() == 0) {
            System.out.println("No flights presented");
        }
        System.out.println("\n-----------------------------------------------------------------------------------");
        return flightByFlightNumber;
    }

    public void showFlightsBy24Hours() {
        System.out.println("FLIGHTS BY 24 HOURS\n" + printInfo);
        flights.getFlightsBy24Hours().stream().forEach(flight -> System.out.println(String.format("%-5d", flights.getFlightsBy24Hours().indexOf(flight) + 1) + flight));
        System.out.println("\n-----------------------------------------------------------------------------------");
    }

}