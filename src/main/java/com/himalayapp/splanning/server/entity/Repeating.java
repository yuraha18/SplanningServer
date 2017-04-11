package com.himalayapp.splanning.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "Repeating")
public class Repeating {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "task_id", nullable = false)
    private long taskId;

    @Column(name = "day_id", nullable = false)
    private long dayId;

    public Repeating() {
    }

    @Override
    public String toString() {
        return "Repeating{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", dayId=" + dayId +
                '}';
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

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }
}


