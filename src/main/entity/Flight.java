package entity;

import Enum.CityArrival;
import Enum.CityDeparture;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Flight {
    private CityDeparture departure = CityDeparture.KIEV;//FROM
    private CityArrival arrival;//TO
    private String flightRoute;//FLIGHT NUMBER
    private Date date;
    private static int countflight = 100;//MAGIC NUMBER
    private int freeSeats;//SEATS WICH ARE NOT BOOKED YET
    private long durationTime;
    private final static int MAX_CAPACITY = 150;//MAGIC NUMBER

    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat durationFormat = new SimpleDateFormat("h'h'mm'm'");
    private SimpleDateFormat defaultFormat = new SimpleDateFormat("dd.MM.yyyy/HH:mm");
    //FOR READING
    public Flight(String raw) {
        this(raw.split("\t"));
    }
    //FOR READING
    public Flight(String raw[]) {
        this(raw[0], raw[1], raw[2], raw[3], raw[4], raw[5], raw[6]);
    }
    //FOR READING
    public Flight(String flightRoute, String departure, String arrival, String date , String time, String durationTime, String freeSeats) {
        try {
            this.departure = CityDeparture.valueOf(departure);
            this.arrival = CityArrival.valueOf(arrival);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        this.flightRoute = flightRoute;
        try {
            String timeAndDate = date + "/" + time;
            this.date = defaultFormat.parse(timeAndDate);

            this.durationTime = durationFormat.parse(durationTime).getTime();
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
        }

        this.freeSeats = Integer.parseInt(freeSeats);
    }
    //FOR TICKETS READING
    public Flight(String flightRoute, String departure, String arrival, String date , String time, String durationTime) {
        try {
            this.departure = CityDeparture.valueOf(departure);
            this.arrival = CityArrival.valueOf(arrival);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        this.flightRoute = flightRoute;
        try {
            String timeAndDate = date + "/" + time;
            this.date = defaultFormat.parse(timeAndDate);

            this.durationTime = durationFormat.parse(durationTime).getTime();
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    //FOR RANDOM
    public Flight(){
        Date tempTime = createRandomDate();
        this.date = tempTime;
        timeFormat.format(tempTime);
        dateFormat.format(tempTime);
        this.flightRoute = this.createflightNum();
        this.freeSeats = this.actualCapacity();
    }

    //FOR RANDOM
    public String createflightNum() {
        countflight++;
        String resultflight = "-" + countflight;
        return resultflight;
    }
    //FOR RANDOM
    public int actualCapacity(){
        return MAX_CAPACITY - (int) (Math.random() * MAX_CAPACITY);
    }
    //FOR RANDOM
    public static Date createRandomDate(){
        long currentTimeMill = new Date().getTime();
        long result = currentTimeMill + (long)(Math.random()*2.5 * 24 * 60 * 60 * 1000);
        Date date = new Date(result);
        return date;
    }

    @Override
    public String toString() {
        return  flightRoute + "\t\t" + departure + "\t\t" +
                String.format("%-12s", arrival) +
                dateFormat.format(date) + "\t" +
                timeFormat.format(date) + "\t" +
                durationFormat.format(durationTime) +
                String.format("%17s", freeSeats);
    }
    //FOR IO
    public String toText() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\n", flightRoute, departure, arrival, dateFormat.format(date), timeFormat.format(date), durationFormat.format(durationTime), freeSeats);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return departure == flight.departure &&
                arrival == flight.arrival &&
                Objects.equals(this.flightRoute, flight.flightRoute) &&
                Objects.equals(date, flight.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, arrival, flightRoute, date);
    }


    //GET|SET
    public CityDeparture getDeparture() {
        return departure;
    }

    public void setDeparture(CityDeparture departure) {
        this.departure = departure;
    }

    public CityArrival getArrival() {
        return arrival;
    }

    public void setArrival(CityArrival arrival) {
        this.arrival = arrival;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public void setFlightRoute(String flightRoute) {
        this.flightRoute = flightRoute;
    }

    public SimpleDateFormat getTimeFormat() {
        return timeFormat;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public long getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(long durationTime) {
        this.durationTime = durationTime;
    }

    public String getFlightRoute() {
        return flightRoute;
    }

    public SimpleDateFormat getDurationFormat() {
        return durationFormat;
    }

}
