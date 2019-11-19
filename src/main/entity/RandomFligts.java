package entity;

import Enum.CityArrival;
import io.DriverFiles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class RandomFligts {
    public List<Flight> make() {
        ArrayList<Flight> flights = new ArrayList<>();
        List<CityArrival> allCities = Arrays.asList(CityArrival.values());

            allCities.stream()
                    .forEach(cityArrival -> {
                        Flight newFlight = new Flight();
                        newFlight.setArrival(cityArrival);
                        String resultId = String.valueOf(newFlight.getDeparture().toString().charAt(0)) + String.valueOf(newFlight.getArrival().toString().charAt(0)) + newFlight.getFlightRoute();
                        newFlight.setFlightRoute(resultId);
                        flights.add(newFlight);

                        long randomNumber = 24 * 60 * 60 * 1000;
                        long time = newFlight.getDate().getTime();

                        long randomDuration = 3 * 60 * 60 * 1000;
                        long randomDurationResult = newFlight.getDate().getTime() + (long) (Math.random() * randomDuration) - 60*60*1000;
                        newFlight.setDurationTime(randomDurationResult - time);

                        for (int i = 0; i < 25; i++) {
                            time += randomNumber;
                            long newDate = time;
                            Date date = new Date(newDate);
                            Flight newFlight1 = new Flight();
                            newFlight1.setDate(newFlight.getDate());
                            newFlight1.setDate(date);
                            newFlight1.setArrival(newFlight.getArrival());
                            newFlight1.setFlightRoute(newFlight.getFlightRoute());
                            newFlight1.setDurationTime(newFlight.getDurationTime());
                            flights.add(newFlight1);
                        }
                    });
        return flights;
    }
    public void writeRandoms(List<Flight> flights){
         StringBuilder sb = new StringBuilder();
         flights.forEach(flight -> sb.append(flight.toText()));
         DriverFiles.writeFile("flights.txt",sb.toString());
    }
}
