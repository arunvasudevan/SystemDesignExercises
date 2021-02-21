package com.avasudevan.meetingscheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class Room {
    private String name;
    private Map<Integer, List<Pair<Integer, Integer>>> calendarMap;

    Room(String name) {
        this.name = name;
        this.calendarMap = new HashMap<>();
    }

    String getName() {
        return this.name;
    }
    public Map<Integer, List<Pair<Integer, Integer>>> getCalendarMap() {
        return calendarMap;
    }

    public void addBooking(int day, int startTime, int endTime) {
        List<Pair<Integer, Integer>> calendar = calendarMap.getOrDefault(day, new ArrayList<>());
        calendar.add(new Pair<>(startTime, endTime));
        Collections.sort(calendar, Comparator.comparingInt(Pair::getKey));
        calendarMap.put(day, calendar);
    }
}
