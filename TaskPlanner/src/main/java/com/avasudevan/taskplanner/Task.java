package com.avasudevan.taskplanner;

public interface Task {
    public void setStatus(TASKSTATUS status);

    public TASKSTATUS getStatus();

    public User getAssigned();

    public String getTitle();
}
