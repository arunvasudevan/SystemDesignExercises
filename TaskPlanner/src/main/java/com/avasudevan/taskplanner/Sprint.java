package com.avasudevan.taskplanner;

import java.util.List;

public class Sprint {
    List<Task> tasks;
    int start, end;

    public Sprint(List<Task> tasks, int start, int end) {
        this.tasks = tasks;
        this.start = start;
        this.end = end;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void printSprintDetails() {

        System.out.println("Start:"+start+", End:"+end);
        tasks.stream().forEach(t -> {
            System.out.println(t.getTitle()+", "+ t.getStatus()+", "+ t.getAssigned().name);
        });
    }
}
