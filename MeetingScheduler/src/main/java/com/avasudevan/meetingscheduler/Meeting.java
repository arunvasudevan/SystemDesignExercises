package com.avasudevan.meetingscheduler;

import java.util.List;

import javafx.util.Pair;

public class Meeting {

    private int startTime, endTime, day;

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Room getRoom() {
        return room;
    }

    public int getDay() { return day; }

    private Room room;

    Meeting() { }

    String book(int day, int startTime, int endTime) {
        Scheduler.getRooms()
            .stream()
            .forEach(r -> {
                boolean isAvailable = true;
                if (r.getCalendarMap().containsKey(day)) {
                    List<Pair<Integer, Integer>> booked = r.getCalendarMap().get(day);

                    for (Pair<Integer, Integer> b : booked) {
                        if ((startTime < b.getValue() && endTime > b.getKey())) {
                            isAvailable = false;
                        }
                    }
                }

                if (this.room == null && isAvailable) {
                    this.startTime = startTime;
                    this.endTime = endTime;
                    this.day = day;
                    r.addBooking(day, startTime, endTime);
                    this.room = r;
                }
                }
            );

        return this.room != null ? this.room.getName() : "No Meeting Rooms Available";
    }
}
