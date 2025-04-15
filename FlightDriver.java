/* --------------------------------------------------------------------
Assignment 4
Written by: Ahmad-Radjai Cherifi, 40327926
For COMP 248 Section U – Winter 2025
--------------------------------------------------------------------
FlightDriver.java is the main file that runs the program. It shows the menu, takes input from the user and calls the right actions(methods) based on the code number.
It controls how the user talks to the system and what happens based on which code they choose. */

import java.util.Scanner;

public class FlightDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Flight flight = new Flight();
        boolean isStart = true;

        // Run program until user exits
        while (true) {
            if (isStart) {
                // Welcome banner
                System.out.print("""
                        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        | Simple Flights Management System (SFMS)                     |
                        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        
                        Code -> Description
                        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        101 -> Define the Flight
                        102 -> Add Pilot to the Flight
                        103 -> Add Flight Attendants to the Flight
                        104 -> Register Passenger to the Flight
                        105 -> Deregister Flight Attendant(s) and/or Passenger(s)
                        106 -> Enter/Update Passenger(s) Charges
                        107 -> Display Passengers' Statistics
                        108 -> Display Flight Statistics
                        109 -> Display Chargesheet
                        110 -> Exit
                        """);

                System.out.println();
                System.out.print("Please enter a Code, from the aforementioned, that corresponds to your task: ");

            } else {
                System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: ");
            }

            int choice = sc.nextInt();
            sc.nextLine();

            // Implementation for each code number
            switch (choice) {
                case 101: {
                    System.out.print("Enter Flight Name, Code, and Permit (Flight,Code,Permit): ");
                    String[] data = sc.nextLine().trim().split(",");
                    flight.setFlightName(data[0]);
                    flight.setFlightCode(data[1]);
                    flight.setFlightPermit(data[2]);
                    break;
                }
                case 102: {
                    System.out.print("Enter Pilot (EmployeeID,FirstName,LastName): ");
                    String[] data = sc.nextLine().split(",");
                    flight.setPilot(new Person(data[0], data[1], data[2]));
                    break;
                }
                case 103: {
                    System.out.print("Enter Flight Attendants (ID1, FirstName1,LastName1;ID2,FirstName2,LastName2): ");
                    Person[] crew = Person.inStrToPersonArr(sc.nextLine());
                    System.out.println(flight.appendToPersonArr(crew, 103));
                    break;
                }
                case 104: {
                    System.out.print("Enter Passengers (ID1, FirstName1,LastName1;ID2,FirstName2,LastName2): ");
                    Person[] pax = Person.inStrToPersonArr(sc.nextLine());
                    System.out.println(flight.appendToPersonArr(pax, 104));
                    break;
                }
                case 105: {
                    System.out.print("Enter IDs to remove (ID1;ID2): ");
                    System.out.println(flight.deleteFromPersonArr(sc.nextLine(), 105));
                    break;
                }
                case 106: {
                    System.out.print("Enter ID,charge pairs (ID1,Charge1;ID2,Charge2): ");
                    System.out.println(flight.updateIndividualCharge(sc.nextLine(), 106));
                    break;
                }
                case 107: {
                    System.out.println("------------------------------");
                    System.out.println("------------------------------");
                    System.out.println("Flight Name   : " + flight.getFlightName());
                    System.out.println("Flight Code   : " + flight.getFlightCode());
                    System.out.println("Flight Permit : " + flight.getFlightPermit());

                    System.out.println("\nPilot Info    : " + flight.getPilot());

                    System.out.println("\nFlight Attendants:");
                    for (Person attendant : flight.getFlightAttend()) {
                        System.out.println(" - " + attendant);
                    }

                    System.out.println("\nPassenger Info:");
                    for (Person p : flight.getPassenger()) {
                        System.out.println(" - " + p + " | Charge: " + p.getChargePercent() + "%");
                    }

                    break;
                }
                case 108: {
                    flight.flightStats();
                    break;
                }
                case 109: {
                    flight.chargeSheet();
                    break;
                }
                case 110: {
                    System.out.println("Exiting...");
                    System.out.println("Thank you for using our Simple Flights Management System (SFMS) ");
                    sc.close();
                    return;

                }
                default: {
                    System.out.println("Invalid option.");
                    break;
                }

            }
            if (isStart) {
                isStart = false;
            }
        }
    }
}
