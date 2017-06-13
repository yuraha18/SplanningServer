package com.himalayapp.splanning.server.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "MonthRepeating")
public class MonthRepeating {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "task_id", nullable = false)
    private long taskId;

    @Column(name = "day_of_month", nullable = false)
    private int dayOfMonth;

    @Transient
    private long localId;

    @Override
    public String toString() {
        return "MonthRepeating{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", dayOfMonth=" + dayOfMonth +
                ", localId=" + localId +
                '}';
    }

    public long getLocalId() {
        return localId;
    }

    public void setLocalId(long localId) {
        this.localId = localId;
    }

    public MonthRepeating() {
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

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthRepeating that = (MonthRepeating) o;

        if (id != that.id) return false;
        if (taskId != that.taskId) return false;
        if (dayOfMonth != that.dayOfMonth) return false;
        return localId == that.localId;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (taskId ^ (taskId >>> 32));
        result = 31 * result + dayOfMonth;
        result = 31 * result + (int) (localId ^ (localId >>> 32));
        return result;
    }
}

