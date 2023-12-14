package org.example;


import java.time.LocalDate;

public class Trip {
    public Trip(int id, LocalDate date, String departure, String arrival) {
        this.id = id;
        this.date = date;
        this.departure = departure;
        this.arrival = arrival;
    }
    private int id;
    private LocalDate date;
    private String departure;
    private String arrival;

    @Override
    public String toString() {
        return "Тур: " + this.getDeparture() + " -> " + this.getArrival() +" (" + this.getDate() + ")";
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }
}