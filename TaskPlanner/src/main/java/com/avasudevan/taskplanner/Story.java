package com.avasudevan.taskplanner;

public class Story implements Task{

    TASKTYPE type;
    User assigned;
    String desc, title;
    TASKSTATUS status;

    Story(User assigned, String title) {
        this.type = TASKTYPE.STORY;
        this.status = TASKSTATUS.TODO;
        this.assigned = assigned;
        this.title = title;
    }

    Story(User assigned, String title, String desc) {
        this.type = TASKTYPE.STORY;
        this.status = TASKSTATUS.TODO;
        this.assigned = assigned;
        this.title = title;
        this.desc = desc;
    }

    @Override
    public void setStatus(TASKSTATUS status) {
        this.status = status;
    }

    @Override
    public TASKSTATUS getStatus() {
        return status;
    }

    @Override
    public User getAssigned() {
        return this.assigned;
    }

    @Override
    public String getTitle() {
        return this.title;
    }
}
