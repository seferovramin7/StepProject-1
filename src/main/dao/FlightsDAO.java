package dao;

import entity.Flight;
import Enum.CityArrival;
import java.util.List;

public interface FlightsDAO {

    List<Flight> getFlightsByArrival(CityArrival cityArrival);
    List<Flight> getFlightsByDate(String date);
    List<Flight> getFlightByFlightNumber(String flightNumber);
    List<Flight> getFlightsBy24Hours();

}
