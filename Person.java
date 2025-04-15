/* --------------------------------------------------------------------
Assignment 4
Written by: Ahmad-Radjai Cherifi, 40327926
For COMP 248 Section U – Winter 2025
--------------------------------------------------------------------
This class represents a person involved in a flight (pilot, attendant, or passenger). It stores their ID, first name, last name, and charge percentage (for passengers).
It also includes utility methods for formatting and comparing person data.*/

public class Person {
    private String entityID;
    private String firstName;
    private String lastName;
    private double chargePercent;

    // Default Constructor
    public Person() {
        this.entityID = "";
        this.firstName = "";
        this.lastName = "";
        this.chargePercent = 0.0;
    }

    // Custom Constructor
    public Person(String inEntityID, String inFirstName, String inLastName) {
        this.entityID = inEntityID;
        this.firstName = upperCamelCase(inFirstName);
        this.lastName = upperCamelCase(inLastName);
        this.chargePercent = 0.0;
    }

    // Copy Constructor
    public Person(Person objPerson) {
        this.entityID = objPerson.entityID;
        this.firstName = objPerson.firstName;
        this.lastName = objPerson.lastName;
        this.chargePercent = objPerson.chargePercent;
    }

    // Getters
    public String getEntityID() { return entityID; }
    public String getFirstname() { return firstName; }
    public String getLastName() { return lastName; }
    public double getChargePercent() { return chargePercent; }

    // Setters
    public void setEntityID(String inEntityID) { this.entityID = inEntityID; }
    public void setFirstName(String inFirstName) { this.firstName = upperCamelCase(inFirstName); }
    public void setLastName(String inLastName) { this.lastName = upperCamelCase(inLastName); }
    public void setChargePercent(double inChargePercent) { this.chargePercent = inChargePercent; }

    // equals override
    public boolean equals(Person anotherPerson) {
        return this.entityID.equals(anotherPerson.getEntityID());
    }

    // toString override
    @Override
    public String toString() {
        return lastName + ", " + firstName + " (" + entityID + ")";
    }

    // Static Utilities
    public static String upperCamelCase(String inStr) {
        if (inStr == null || inStr.isEmpty()) return "";
        inStr = inStr.trim();
        return inStr.substring(0, 1).toUpperCase() + inStr.substring(1).toLowerCase();
    }

    // Converts a sentence to UpperCamelCase for each word
    public static String multiUpperCamelCase(String inStr) {
        String[] words = inStr.trim().split("\\s+");
        String result = "";
        for (String word : words) {
            result += upperCamelCase(word) + " ";
        }
        return result.trim(); // Remove the extra space at the end
    }


    public static String upperCase(String inStr) {
        return inStr.trim().toUpperCase();
    }

    // Converts a string into a Person array
    public static Person[] inStrToPersonArr(String inStr) {
        String[] entries = inStr.split(";");
        Person[] people = new Person[entries.length];
        for (int i = 0; i < entries.length; i++) {
            String[] parts = entries[i].split(",");
            people[i] = new Person(parts[0], parts[1], parts[2]);
        }
        return people;
    }
}
