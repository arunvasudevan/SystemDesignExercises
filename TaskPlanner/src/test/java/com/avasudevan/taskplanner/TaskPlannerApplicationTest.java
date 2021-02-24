package com.avasudevan.taskplanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TaskPlannerApplicationTest {

    TaskPlanner taskPlanner;
    User user1, user2;

    @Before
    public void init() {
        // Add Users
        user1 = new User( "user-1", 1);
        user2 = new User( "user-2", 2);
    }

    @Test
    public void testDelayedTasks() {
        // Add Tasks
        Story story = new Story(user1, "Story-1", "Story-Desc-1");
        // Add Sprints
        List<Task> tasks = new ArrayList<>();
        tasks.add(story);
        Sprint sprint = new Sprint(tasks, 1, 2);
        // Create Planner
        taskPlanner = new TaskPlanner(Arrays.asList(sprint));
        sprint.printSprintDetails();
        // Test Assigned Tasks for User
        Assert.assertEquals(tasks, taskPlanner.getDelayedTask());
    }


    @Test
    public void testUserAssignedTasks() {
        // Add Tasks
        Story story1 = new Story(user1, "Story-1", "Story-Desc-1");
        Story story2 = new Story(user1, "Story-2", "Story-Desc-2");
        // Add Sprints
        List<Task> tasks = new ArrayList<>();
        tasks.add(story1);
        tasks.add(story2);
        List<Task> expectedTask = new ArrayList<>(tasks);
        Sprint sprint = new Sprint(tasks, 1, 2);
        sprint.addTask(new Story(user2, "Story-User-2", "Story-Desc-User-2"));
        // Create Planner
        taskPlanner = new TaskPlanner(Arrays.asList(sprint));
        sprint.printSprintDetails();
        // Test Assigned Tasks for User
        Assert.assertEquals(expectedTask, taskPlanner.getTasks(1));
    }
}
