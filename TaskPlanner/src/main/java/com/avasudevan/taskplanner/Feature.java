package com.avasudevan.taskplanner;

public class Feature implements Task{
    TASKTYPE type;
    User assigned;
    String desc, title;
    TASKSTATUS status;

    Feature(User assigned, String title, String desc) {
        this.type = TASKTYPE.FEATURE;
        this.assigned = assigned;
        this.title = title;
        this.desc =desc;
        this.status = TASKSTATUS.TODO;
    }

    @Override
    public void setStatus(TASKSTATUS status) {
        this.status = status;
    }

    @Override
    public TASKSTATUS getStatus() {
        return this.status;
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
