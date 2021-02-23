package com.avasudevan.taskplanner;

public class Bug implements Task{
    TASKTYPE type;
    User assigned;
    String desc, title;
    TASKSTATUS status;

    public Bug(User assigned, String desc, String title) {
        this.type = TASKTYPE.BUG;
        this.assigned = assigned;
        this.desc = desc;
        this.title = title;
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
