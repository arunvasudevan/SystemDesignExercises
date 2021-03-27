package com.avasudevan.jobscheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Scheduler {

    private final List<Job> jobs;

    private final PriorityQueue<Job> sjfQ;
    private final PriorityQueue<Job> fcfsQ;
    private final PriorityQueue<Job> fpsQ;
    private final PriorityQueue<Job> edfQ;

    public Scheduler(List<Job> jobs) {
        this.jobs = jobs;
        this.sjfQ = new PriorityQueue<>(Comparator.comparingInt(Job::getDuration));
        this.sjfQ.addAll(jobs);
        this.fcfsQ = new PriorityQueue<>(Comparator.comparingInt(Job::getId));
        this.fcfsQ.addAll(jobs);
        this.fpsQ = new PriorityQueue<>(Comparator.comparingInt(Job::getPriority)
            .thenComparing(Job::getUser)
            .thenComparingInt(Job::getDuration));
        this.fpsQ.addAll(jobs);
        this.edfQ = new PriorityQueue<>(Comparator.comparingInt(Job::getDeadline)
            .thenComparing(Job::getPriority)
            .thenComparing(Job::getDuration));
        this.edfQ.addAll(jobs);
    }

    Map<String, List<List<Job>>> getSchedulingSequence(SchedulingAlgorithm schedulingAlgorithm, int noOfThreads) {
        Map<String, List<List<Job>>> schedulingSequence = new HashMap<>();

        switch (schedulingAlgorithm) {
            case EDF:
                schedulingSequence.put("Earliest Deadline First", getEDFOrder(noOfThreads));
                break;
            case FPS:
                schedulingSequence.put("Fixed Priority Scheduling", getFPSOrder(noOfThreads));
                break;
            case SJF:
                schedulingSequence.put("Shortest Job First", getSJFOrder(noOfThreads));
                break;
            case FCFS:
                schedulingSequence.put("First Come First Serve", getFCFSOrder(noOfThreads));
                break;
        }

        return schedulingSequence;
    }

    private List<List<Job>> getFCFSOrder(int noOfThreads) {
        return getJobListByThreads(noOfThreads, fcfsQ);
    }

    private List<List<Job>> getSJFOrder(int noOfThreads) {
        return getJobListByThreads(noOfThreads, sjfQ);
    }

    private List<List<Job>> getFPSOrder(int noOfThreads) {
        return getJobListByThreads(noOfThreads, fpsQ);
    }

    private List<List<Job>> getJobListByThreads(int noOfThreads, PriorityQueue<Job> jobPriorityQueue) {
        List<List<Job>> lists = new ArrayList<>(noOfThreads);
        List<Integer> threadCapacity = new ArrayList<>(noOfThreads);

        for(int i = 0; i < noOfThreads; i++) {
            lists.add(new ArrayList<>());
            threadCapacity.add(0);
        }

        while(!jobPriorityQueue.isEmpty()) {
            for(int i = 0; i < noOfThreads; i++) {
                if(threadCapacity.get(i) == 0) {
                    Job job = jobPriorityQueue.poll();
                    lists.get(i).add(job);
                    threadCapacity.set(i, threadCapacity.get(i) + job.getDuration());
                }
            }
            int minValue = Collections.min(threadCapacity);
            for(int j = 0; j < threadCapacity.size(); j++) {
                threadCapacity.set(j, threadCapacity.get(j) - minValue);
            }
        }
        return lists;
    }

    private List<List<Job>> getEDFOrder(int noOfThreads) {
        List<List<Job>> lists = new ArrayList<>(noOfThreads);
        List<Integer> threadCapacity = new ArrayList<>(noOfThreads);
        int[] totalCapacityPerThread = new int[noOfThreads];

        for(int i = 0; i < noOfThreads; i++) {
            lists.add(new ArrayList<>());
            threadCapacity.add(0);
        }

        while(!edfQ.isEmpty()) {
            for(int i = 0; i < noOfThreads; i++) {
                if(threadCapacity.get(i) == 0) {
                    Job job = edfQ.peek();
                    if(totalCapacityPerThread[i] + job.getDuration() <= job.getDeadline()) {
                        lists.get(i).add(job);
                        threadCapacity.set(i, threadCapacity.get(i) + job.getDuration());
                        totalCapacityPerThread[i] += job.getDuration();
                    }
                    edfQ.poll();
                }
            }
            int minValue = Collections.min(threadCapacity);
            for(int j = 0; j < threadCapacity.size(); j++) {
                threadCapacity.set(j, threadCapacity.get(j) - minValue);
            }
        }
        return lists;
    }
}
