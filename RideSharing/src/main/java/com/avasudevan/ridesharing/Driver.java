package com.avasudevan.ridesharing;

public class Driver {
    private Person person;
    private DriverStatus status;

    Driver(String name) {
        this.person = new Person(name);
        this.status = DriverStatus.AVAILABLE;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }
}
