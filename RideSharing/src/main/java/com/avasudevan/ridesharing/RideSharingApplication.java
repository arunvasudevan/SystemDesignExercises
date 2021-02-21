package com.avasudevan.ridesharing;

public class RideSharingApplication {

    public static void main(String[] args) {

        DriverRepository.createDriver("driver-1");
        DriverRepository.createDriver("driver-2");

        Rider rider1 = new Rider("rider-1");
        Rider rider2 = new Rider("rider-2");
        Rider rider3 = new Rider("rider-3");

        rider1.createRide(1,5, 10, 1);
        rider1.updateRide(1, 5, 12, 2);

        rider2.createRide(2,5, 10, 1);
        rider2.updateRide(2, 5, 12, 2);
        rider2.withdrawRide(2);

        rider3.createRide(3,8, 10, 1);

        System.out.println("Fare:"+rider1.closeRide(1));
        System.out.println("Fare:"+rider3.closeRide(3));

    }
}
