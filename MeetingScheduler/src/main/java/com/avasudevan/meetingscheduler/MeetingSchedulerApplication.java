package com.avasudevan.meetingscheduler;

public class MeetingSchedulerApplication {

    public static void main(String[] args) {
        Scheduler.addRooms(new Room("room-1"));
        Scheduler.addRooms(new Room("room-2"));
        Scheduler.addRooms(new Room("room-3"));

        Scheduler.book(1, 1, 5);
        Scheduler.book(2, 1, 5);
        Scheduler.book(1, 1, 5);
        Scheduler.book(1, 1, 5);

        for(Meeting meeting: Scheduler.getMeetingList()) {
            System.out.println(meeting.getDay() +","+ meeting.getStartTime() +","+ meeting.getEndTime() + "," + meeting.getRoom().getName());
        }

    }
}
