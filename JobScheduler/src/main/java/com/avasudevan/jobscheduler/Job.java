package com.avasudevan.jobscheduler;

public class Job {

    private final String name;

    private final int duration;

    private final int priority;

    private final int deadline;

    private final USERTYPE user;

    private final int id;
    private static int jobId = 1;

    public Job(String name, int duration, int priority, int deadline, USERTYPE user) {
        this.name = name;
        this.duration = duration;
        this.priority = priority;
        this.deadline = deadline;
        this.user = user;
        id = jobId++;
    }

    public int getDuration() {
        return duration;
    }

    public int getPriority() {
        return priority;
    }

    public int getDeadline() {
        return deadline;
    }

    public USERTYPE getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void printJobDetails() {
        System.out.println(name + ", " + duration + ", " + priority + ", " + deadline + ", " + user.name());
    }
}
