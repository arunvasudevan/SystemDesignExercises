package com.avasudevan.meetingscheduler;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final static List<Room> rooms = new ArrayList<>();
    private final static List<Meeting> meetingList = new ArrayList<>();

    public static void addRooms(Room room) {
        rooms.add(room);
    }

    static List<Room> getRooms() {
        return rooms;
    }

    static String book(int day, int startTime, int endTime) {
        Meeting meeting = new Meeting();
        String result = meeting.book(day, startTime, endTime);

        if(!result.contains("No Meeting Rooms Available")) {
            meetingList.add(meeting);
        }
        return result;
    }


    static List<Meeting> getMeetingList() {
        return meetingList;
    }
}
