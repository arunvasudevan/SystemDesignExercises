package com.avasudevan.ridesharing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverRepository {
    static List<Driver> driverList = new ArrayList<>();

    public static void createDriver(String name) {
        Driver driver = new Driver(name);
        driverList.add(driver);
    }

    public static Optional<Driver> getAvailableDriver() {
        return driverList.stream().filter(d -> d.getStatus().equals(DriverStatus.AVAILABLE)).findFirst();
    }
}
