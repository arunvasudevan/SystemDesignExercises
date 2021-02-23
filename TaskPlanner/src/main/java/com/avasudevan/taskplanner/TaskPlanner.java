package com.avasudevan.taskplanner;

import java.util.ArrayList;
import java.util.List;

public class TaskPlanner {

    List<Sprint> sprints;

    public TaskPlanner(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    List<Task> getTasks(int userid) {
        List<Task> taskList = new ArrayList<>();
        sprints.stream()
            .forEach(s -> {
                for(Task task: s.getTasks()) {
                    if(task.getAssigned().id == userid) {
                        taskList.add(task);
                    }
                }
            });
        return taskList;
    }


    List<Task> getDelayedTask() {
        List<Task> taskList = new ArrayList<>();
        sprints.stream()
            .forEach(s -> {
                for(Task task: s.getTasks()) {
                    if(task.getStatus() != TASKSTATUS.COMPLETED) {
                        taskList.add(task);
                    }
                }
            });
        return taskList;
    }
}
