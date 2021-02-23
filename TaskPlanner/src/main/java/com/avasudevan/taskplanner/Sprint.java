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

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
