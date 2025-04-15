/* --------------------------------------------------------------------
Assignment 4
Written by: Ahmad-Radjai Cherifi, 40327926
For COMP 248 Section U – Winter 2025
--------------------------------------------------------------------
This class represents a flight. It stores flight details like name, code, and permit, and holds the assigned pilot, attendants, and passengers.
It provides methods to add/remove people and display flight statistics and charges.*/

public class Flight {
    private String flightName;
    private String flightCode;
    private String flightPermit;
    private Person pilot;
    private Person[] flightAttend;
    private Person[] passenger;

    public Flight() {
        flightName = "";
        flightCode = "";
        flightPermit = "";
        pilot = null;
        flightAttend = new Person[0];
        passenger = new Person[0];
    }

    public Flight(String inFlightName, String inFlightCode, String inFlightPermit, Person inPilot, Person[] inFlightAttend, Person[] inPassenger) {
        flightName = inFlightName;
        flightCode = inFlightCode;
        flightPermit = inFlightPermit;
        pilot = inPilot;
        this.flightAttend = inFlightAttend;
        this.passenger = inPassenger;
    }

    // Getters
    public String getFlightName() { return flightName; }
    public String getFlightCode() { return flightCode; }
    public String getFlightPermit() { return flightPermit; }
    public Person getPilot() { return pilot; }
    public Person[] getFlightAttend() { return flightAttend; }
    public Person[] getPassenger() { return passenger; }

    // Setters
    public void setFlightName(String inFlightName) { flightName = inFlightName; }
    public void setFlightCode(String inFlightCode) { flightCode = inFlightCode; }
    public void setFlightPermit(String inFlightPermit) { flightPermit = inFlightPermit; }
    public void setPilot(Person inPilot) { pilot = inPilot; }
    public void setFlightAsst(Person[] inFlightAttend) { this.flightAttend = inFlightAttend; }
    public void setPassenger(Person[] inPassenger) { this.passenger = inPassenger; }

    @Override
    public String toString() {
        return "Flight: " + flightName + " (" + flightCode + "), Permit: " + flightPermit +
                ", Pilot: " + pilot + ", Attendants: " + flightAttend.length +
                ", Passengers: " + passenger.length;
    }

    // Adds new persons to the array
    public String appendToPersonArr(Person[] newPersons, int mode) {
        Person[] target = (mode == 103) ? flightAttend : passenger;
        Person[] combined = new Person[target.length + newPersons.length];

        for (int i = 0; i < target.length; i++) {
            combined[i] = target[i];
        }
        for (int i = 0; i < newPersons.length; i++) {
            combined[target.length + i] = newPersons[i];
        }

        // Update the corresponding array
        if (mode == 103) flightAttend = combined;
        else passenger = combined;

        return newPersons.length + " person(s) added.";
    }


    // Removes persons from flightAttend or passenger using their IDs
    public String deleteFromPersonArr(String inStr, int mode) {
        String[] ids = inStr.split(";");
        Person[] target = (mode == 105) ? flightAttend : passenger;

        // Temp array
        Person[] temp = new Person[target.length];
        int count = 0;

        for (Person p : target) {
            boolean toRemove = false;
            for (String id : ids) {
                if (p.getEntityID().equals(id)) {
                    toRemove = true;
                    break;
                }
            }
            if (!toRemove) {
                temp[count++] = p;
            }
        }
        Person[] updated = new Person[count];
        for (int i = 0; i < count; i++) {
            updated[i] = temp[i];
        }

        int removed = target.length - count;
        if (mode == 105) flightAttend = updated;
        else passenger = updated;

        return removed + " person(s) removed.";
    }



    // Updates charge percentage for matching passengers using ID
    public String updateIndividualCharge(String inStr, int mode) {
        String[] updates = inStr.split(";");
        StringBuilder result = new StringBuilder();

        for (String entry : updates) {
            String[] pair = entry.trim().split(",");
            if (pair.length != 2) {
                result.append("Invalid input format for entry: ").append(entry).append("\n");
                continue;
            }

            String id = pair[0].trim();
            double charge;
            try {
                charge = Double.parseDouble(pair[1].trim());
            } catch (NumberFormatException e) {
                result.append("Invalid charge value for ID ").append(id).append(".\n");
                continue;
            }

            boolean found = false;
            for (Person p : passenger) {
                if (p.getEntityID().equals(id)) {
                    p.setChargePercent(charge);
                    result.append("Charge for passenger ID ").append(id)
                            .append(" updated to ").append(charge).append("%.\n");
                    found = true;
                    break;
                }
            }

            if (!found) {
                result.append("Passenger ID ").append(id).append(" not found.\n");
            }
        }

        return result.toString();
    }




    // Prints basic flight statistics
    public void flightStats() {
        System.out.println("Flight: " + flightName);
        System.out.println("Pilot: " + pilot);
        System.out.println("Number of Flight Attendants: " + flightAttend.length);
        System.out.println("Number of Passengers: " + passenger.length);
    }

    // Prints the charge sheet for all passengers
    public void chargeSheet() {
        System.out.println("Charge Sheet for Flight: " + flightName);
        for (Person p : passenger) {
            System.out.println(p + " | Charge: " + p.getChargePercent() + "%");
        }
    }
}
