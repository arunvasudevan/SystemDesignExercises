package com.avasudevan.meetingscheduler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MeetingSchedulerAppTest {

    @Before
    public void init() {
        Scheduler.addRooms(new Room("room-1"));
        Scheduler.addRooms(new Room("room-2"));
        Scheduler.addRooms(new Room("room-3"));
    }

    @Test
    public void testWithBookingSameTimeInAllRooms() {
        Assert.assertEquals("room-1", Scheduler.book(1, 1, 5));
        Assert.assertEquals("room-2", Scheduler.book(1, 1, 5));
        Assert.assertEquals("room-3", Scheduler.book(1, 1, 5));
        Assert.assertEquals("No Meeting Rooms Available", Scheduler.book(1, 1, 5));
    }

    @Test
    public void testMultipleDays() {
        Assert.assertEquals("room-1", Scheduler.book(1, 1, 5));
        Assert.assertEquals("room-1", Scheduler.book(2, 1, 5));
        Assert.assertEquals("room-2", Scheduler.book(1, 1, 5));
        Assert.assertEquals("room-2", Scheduler.book(2, 1, 5));
    }
}
