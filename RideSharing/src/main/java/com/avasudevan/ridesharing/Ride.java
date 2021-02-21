package com.avasudevan.ridesharing;

public class Ride {

    private int id, src, dest, seats;
    private Driver driver;

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    private RideStatus status;

     Ride(int id, int src, int dest, int seats, Driver driver) {
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.seats = seats;
        this.driver = driver;
        this.status = RideStatus.CREATED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
