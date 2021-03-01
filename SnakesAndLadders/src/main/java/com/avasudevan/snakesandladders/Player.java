package com.avasudevan.snakesandladders;

public class Player {

    String name;
    String pin;
    int id;
    int position;
    static int idValue = 0;

    public Player(String name, String pin) {
        this.name = name;
        this.pin = pin;
        this.position = 0;
        this.id = ++idValue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public int getId() {
        return id;
    }
}
