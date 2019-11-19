package controller;

import entity.Flight;
import entity.Flights;
import entity.Ticket;
import service.FlightService;
import service.TicketService;
import Enum.CityArrival;

import java.util.List;

public class Controller {

    private FlightService flightService = new FlightService();
    private TicketService ticketService = new TicketService();

    public List<Flight> bookTicket(CityArrival cityArrival, String date, int numberOfPassengers) {
        return flightService.getFlightByArrivalCityAndDateAndShow(cityArrival, date, numberOfPassengers);
    }

    public void onlineBoard() {
        flightService.showFlightsBy24Hours();
    }

    public void flightWithFlightNum(String fligthNumber) {
        flightService.getFligthByFlightNumber(fligthNumber);
    }

    public void getFlightsByDate(String date) {
        flightService.getFlightByDate(date);
    }

    public void createTickets(Flight flight, String name, String surname) {
        ticketService.createTicket(flight, name, surname);
    }

    public void displayTickets(String name, String surname) {
        ticketService.showUserTickets(name, surname);
    }

    public void removeTicket(int id) {
        Flight flight1 = flightService.getAllFlights().stream().filter(flight -> flight.equals(ticketService.getTicketById(id).getFlight())).findFirst().get();
        flight1.setFreeSeats(flight1.getFreeSeats()+1);
        ticketService.removeTicket(id);
    }

    public void displayAllFlights() {
        flightService.displayAllFlights();
    }

    public void displayAllTickets() {
        ticketService.displayAllTicket();
    }

    public List<Ticket> getAllTickets(){return ticketService.getAllTickets();}

    public List<Flight> getAllFlights(){return flightService.getAllFlights();}

}
