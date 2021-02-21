package com.avasudevan.meetingscheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.util.Pair;

public class Room {
    private String name;
    private List<Pair<Integer, Integer>> calendar;

    Room(String name) {
        this.name = name;
        this.calendar = new ArrayList<>();
    }

    String getName() {
        return this.name;
    }
    public List<Pair<Integer, Integer>> getCalendar() {
        return calendar;
    }

    public void addBooking(int startTime, int endTime) {
        calendar.add(new Pair<>(startTime, endTime));
        Collections.sort(calendar, Comparator.comparingInt(Pair::getKey));
    }
}
