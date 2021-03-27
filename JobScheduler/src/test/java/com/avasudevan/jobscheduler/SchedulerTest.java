package com.avasudevan.jobscheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SchedulerTest {

    Scheduler scheduler;

    @Before
    public void init() {
        List<Job> jobs = new ArrayList<>();

        Job job1 = new Job("Job1", 10, 1, 25, USERTYPE.ROOT);
        Job job2 = new Job("Job2", 5, 0, 15, USERTYPE.ADMIN);
        Job job3 = new Job("Job3", 30, 0, 30, USERTYPE.USER);
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);

        scheduler = new Scheduler(jobs);
    }

    @Test
    public void testEDFSchedulingSequence() {
        for(Map.Entry<String, List<List<Job>>> entry: scheduler.getSchedulingSequence(SchedulingAlgorithm.EDF, 2).entrySet()) {
            System.out.println(entry.getKey() + ":");
            int counter = 1;
            for(List<Job> jobs: entry.getValue()) {
                System.out.print("Thread" + counter++ + ": ");
                for(Job job: jobs) {
                    System.out.print(job.getName() + " ");
                }
                System.out.println();
            }
        }
    }

    @Test
    public void testSJFSchedulingSequence() {
        for(Map.Entry<String, List<List<Job>>> entry: scheduler.getSchedulingSequence(SchedulingAlgorithm.SJF, 2).entrySet()) {
            System.out.println(entry.getKey() + ":");
            int counter = 1;
            for(List<Job> jobs: entry.getValue()) {
                System.out.print("Thread" + counter++ + ": ");
                for(Job job: jobs) {
                    System.out.print(job.getName() + " ");
                }
                System.out.println();
            }
        }
    }

    @Test
    public void testFCFSSchedulingSequence() {
        for(Map.Entry<String, List<List<Job>>> entry: scheduler.getSchedulingSequence(SchedulingAlgorithm.FCFS, 2).entrySet()) {
            System.out.println(entry.getKey() + ":");
            int counter = 1;
            for(List<Job> jobs: entry.getValue()) {
                System.out.print("Thread" + counter++ + ": ");
                for(Job job: jobs) {
                    System.out.print(job.getName() + " ");
                }
                System.out.println();
            }
        }
    }

    @Test
    public void testFPSSchedulingSequence() {
        for(Map.Entry<String, List<List<Job>>> entry: scheduler.getSchedulingSequence(SchedulingAlgorithm.FPS, 2).entrySet()) {
            System.out.println(entry.getKey() + ":");
            int counter = 1;
            for(List<Job> jobs: entry.getValue()) {
                System.out.print("Thread" + counter++ + ": ");
                for(Job job: jobs) {
                    System.out.print(job.getName() + " ");
                }
                System.out.println();
            }
        }
    }
}
