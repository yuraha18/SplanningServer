package com.himalayapp.splanning.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TaskLifecycle")
public class TaskLifecycle {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "task_id", nullable = false)
    private long taskId;

    @Column(name = "day_from_id", nullable = false)
    private long dayFromId;

    @Column(name = "day_to_id", nullable = false)
    private long dayToId;

    public TaskLifecycle() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getDayFromId() {
        return dayFromId;
    }

    public void setDayFromId(long dayFromId) {
        this.dayFromId = dayFromId;
    }

    public long getDayToId() {
        return dayToId;
    }

    public void setDayToId(long dayToId) {
        this.dayToId = dayToId;
    }
}


