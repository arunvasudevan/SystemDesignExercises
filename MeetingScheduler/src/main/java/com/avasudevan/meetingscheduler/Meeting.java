package com.avasudevan.meetingscheduler;

import java.util.List;

import javafx.util.Pair;

public class Meeting {

    private int startTime, endTime;

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Room getRoom() {
        return room;
    }

    private Room room;

    Meeting() { }

    String book(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;

        Scheduler.getRooms()
            .stream()
            .forEach(r -> {
                    List<Pair<Integer, Integer>> booked = r.getCalendar();
                    boolean isAvailable = true;

                    for(Pair<Integer, Integer> b: booked) {
                        if((startTime < b.getValue() && endTime > b.getKey())) {
                            isAvailable = false;
                        }
                    }

                    if(this.room == null && isAvailable) {
                        r.addBooking(startTime, endTime);
                        this.room = r;
                    }
                }
            );

        return room != null ? room.getName() : "No Meeting Rooms Available";
    }
}
