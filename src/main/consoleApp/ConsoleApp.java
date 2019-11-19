package consoleApp;

import controller.Controller;
import Enum.CityArrival;
import entity.Flight;
import io.DriverFiles;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String REG_NUM = "([0-9])+";
    private static final String REG_STR = "\\D+";
    private static final String REG_FLIGHTS = "[a-zA-z][0-9][1-9]";
    private static final String REG_MENU = "[1-6]";
    private static final String REG_DATE = "((1)[0-9]|(2)[0-9]|0[1-9]|3[0-1]).(1[0-2]|0[1-9]).([0-9]){4}";
    private static final String REG_MAX_PAX = "[1-4]";

    public void consoleRun() {
        Controller controller = new Controller();

        System.out.println("************** START CONSOLE APP **************\n");
        int command;
        do {
            printCommand();
            System.out.print("Please enter the command: ");
            switch (command = Integer.parseInt(validEnter(REG_MENU, "\nEnter the command(from 1 to 6): "))) {

                case 1:
                    boardsOnline(controller);
                    break;

                case 2:
                    routInfo(controller);
                    break;

                case 3:
                    System.out.print("- 3. SEARCH FLIGHT AND BOOKING: \n\t- Enter arrival city: ");
                    CityArrival cityArrival = CityArrival.valueOf(validCity());

                    System.out.print("\t- Enter date of flight(ex. 10.11.2019): ");
                    String date = validEnter(REG_DATE, "\n\t- Enter date of flight(ex. 11.11.2019): ");

                    System.out.print("\t- Enter quantity of passengers: ");
                    int pax = Integer.parseInt(validEnter(REG_MAX_PAX, "\n\t- Enter quantity of passengers \n\t* (Can't book more than for 4 persons): "));
                    List<Flight> listBook = controller.bookTicket(cityArrival, date, pax);

                    if (listBook.isEmpty()) {
                        break;
                    } else {
                        boolean boilerplate = true;
                        while (boilerplate) {
                            try {
                                bookFlight(controller, pax, listBook);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("NO FLIGHT WITH SUCH INDEX\n");
                            }
                            boilerplate = false;
                        }
                    }
                    break;
                case 4:
                    System.out.println();
                    System.out.print(
                            "- 4. CANCEL OF BOOKING: \n\t- Enter ID of booking(or enter 0 to return main menu): ");
                    try {
                        controller.removeTicket(Integer.parseInt(SCANNER.nextLine().toUpperCase()));
                        System.out.println();
                        System.out.println("Your booking is cancel");
                        System.out.println();
                    } catch (Exception e) {
                        System.out.print("\t- You entered incorrect ID");
                        break;
                    }
                    break;
                case 5:
                    showMyFlights(controller);
                    break;
                case 6:
                    StringBuilder sb = new StringBuilder();
                    controller.getAllTickets().forEach(ticket -> sb.append(ticket.toText()));
                    DriverFiles.writeFile("tickets.txt", sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    controller.getAllFlights().forEach(flight -> sb2.append(flight.toText()));
                    DriverFiles.writeFile("flights.txt", sb2.toString());
                    System.out.println();
                    System.out.println("- Stop the console app.\n");
                    break;
            }
        } while (command != 6);
        System.out.println("************** END CONSOLE APP **************");
    }

    //COMMANDS
    public void printCommand() {
        System.out.println(
                "\nLIST OF COMMAND: \n" +
                        "- 1. ONLINE BOARD\n" +
                        "- 2. VIEW FLIGHT INFORMATION\n" +
                        "- 3. SEARCH FLIGHT AND BOOKING: \n" +
                        "- 4. CANCEL OF BOOKING: \n" +
                        "- 5. SHOW MY FLIGHTS: \n" +
                        "- 6. EXIT FROM BOOKING \n");
    }

    private void boardsOnline(Controller controller) {
        System.out.println("\n- 1. ONLINE BOARD\n");
        controller.onlineBoard();
        System.out.println();
    }

    private void routInfo(Controller controller) {
        System.out.println();
        System.out.print("- 2. VIEW FLIGHT INFORMATION\n\t- Enter Flight№ of flight(ex. P07): ");
        String flightNumber = validEnter(REG_FLIGHTS, "\n\t- Enter Flight№ of flight(ex. P07): ");
        controller.flightWithFlightNum(flightNumber);
        System.out.println();
    }

    private void bookFlight(Controller controller, int pax, List<Flight> listBook) {
        System.out.print("- Enter index of flight (or enter 0 to return main menu): ");
        int index = Integer.parseInt(validEnter(REG_NUM, "- Enter index of flight: "));
        if (index != 0) {
            for (int i = 0; i < pax; i++) {
                System.out.print("\t- Enter name: ");
                String name = validEnter(REG_STR, "\n\t- Enter name: ");
                System.out.print("\t- Enter surname: ");
                String surname = validEnter(REG_STR, "\n\t- Enter surname: ");
                controller.createTickets(listBook.get(index - 1), name, surname);
            }
            controller.displayAllTickets();
            System.out.println();
            System.out.println("Your booking is finished successful");
            System.out.println();
        }
    }

    private void showMyFlights(Controller controller) {
        System.out.print(
                "- 5. SHOW MY FLIGHTS: \n" +
                        "\t- Enter name:");
        String name = validEnter(REG_STR, "\t\n- Enter name: ");
        System.out.print("\t- Enter surname: ");
        String surname = validEnter(REG_STR, "\t\n- Enter surname: ");
        System.out.print("\n" + name + " " + surname + "\n");
        controller.displayTickets(name, surname);
    }

    //ENTER SOME DATA
    private String validEnter(String regex, String prntInfo) {
        String string = SCANNER.nextLine();
        while (!string.matches(regex)) {
            System.out.print("\nWRONG DATA -> " + string);
            System.out.print(prntInfo);
            string = SCANNER.nextLine();
        }
        return string.toUpperCase();
    }

    private String validCity() {
        String string = validEnter(REG_STR, "\t- Enter city: ");
        boolean newBoiler = true;
        while (newBoiler) {
            try {
                if (CityArrival.valueOf(string).ordinal() == -1) {
                    System.out.println("WE DON'T FLIGHT TO THIS CITY\n");
                }
                newBoiler = false;
            } catch (IllegalArgumentException e) {
                System.out.print("\nWRONG DATA -> " + string + "\n\t- Enter arrival city: ");
                string = validEnter(REG_STR, "\n\t- Enter arrival city: ");
            }
        }
        return string.toUpperCase();
    }
}