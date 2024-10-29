package model;

import java.time.LocalDate;

public class Training {


    // fields
    private int id;
    private String type; // walking, running, jumping
    private LocalDate date;
    private double distance; // в километрах
    private double duration; // в часах

    // constructor
    public Training(int id, String type, LocalDate date, double distance, double duration) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }

}
