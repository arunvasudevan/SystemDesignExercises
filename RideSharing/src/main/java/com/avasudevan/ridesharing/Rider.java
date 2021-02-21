package com.avasudevan.ridesharing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Rider {
    private Person person;
    private List<Ride> allRides = new ArrayList<>();
    public static final int AMOUNT_PER_KM = 20;

    public Rider(String name) {
        this.person = new Person(name);
    }

    boolean createRide(int id, int src, int dest, int seats) {
        Optional<Driver> d = DriverRepository.getAvailableDriver();
        if (!d.isPresent()) {
            System.out.println("No Driver Available for Ride "+id+" ,Retry after sometime");
            return false;
        }
        Ride ride = new Ride(id, src, dest, seats, d.get());
        d.get().setStatus(DriverStatus.BUSY);
        allRides.add(ride);
        return true;
    }

    boolean updateRide(int id,int src, int dest, int seats) {
        List<Ride> rides = allRides.stream().filter(r -> r.getId() == id).collect(Collectors.toList());
        if (rides.size() == 0) {
            return createRide(id, src, dest, seats);
        }
        if (rides.size() > 1) {
            System.out.println("More than one ride with same id");
            return false;
        }

        rides.stream().forEach(r -> {
            if (r.getStatus().equals(RideStatus.COMPLETED) || r.getStatus().equals(RideStatus.WITHDRAWN)) {
                System.out.println("Closed or Withdrawn ride cannot be updated");
            } else {
                r.setSrc(src);
                r.setDest(dest);
                r.setSeats(seats);
            }
        });
        return true;
    }

    void withdrawRide(int id) {
        Optional<Ride> ride = allRides.stream().filter(r -> r.getId() == id).findFirst();

        if(!ride.isPresent()) {
            System.out.println("No Ride withthat id present");
        }
        ride.get().setStatus(RideStatus.WITHDRAWN);
        ride.get().getDriver().setStatus(DriverStatus.AVAILABLE);
        allRides.remove(ride.get());
    }

    int closeRide(int id) {
        List<Ride> rides = allRides.stream().filter(r -> r.getId() == id).collect(Collectors.toList());

        if (rides.size() == 0) {
            System.out.println("No ride with that ID exists");
        }
        AtomicInteger fare = new AtomicInteger();
        rides.stream().forEach(
            ride -> {
                if (ride.getStatus().equals(RideStatus.COMPLETED) || ride.getStatus().equals(RideStatus.WITHDRAWN)) {
                    System.out.println("Ride has been closed or withdrawn already");
                    fare.set(0);
                } else {
                    ride.getDriver().setStatus(DriverStatus.AVAILABLE);
                    ride.setStatus(RideStatus.COMPLETED);
                    fare.set(calculateFare(id));
                }

            }
        );
        return fare.get();
    }

    private int calculateFare(int id) {
        Ride ride = allRides.stream().filter(r -> r.getId() == id).findFirst().get();

        if (ride.getSeats() <= 1) {
            return (ride.getDest() - ride.getSrc()) * AMOUNT_PER_KM * ride.getSeats();
        }
        return (ride.getDest() - ride.getSrc()) * AMOUNT_PER_KM * (int) (ride.getSeats() * 0.75);
    }
}
