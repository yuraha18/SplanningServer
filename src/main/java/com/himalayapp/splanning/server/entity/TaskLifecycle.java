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

    @Override
    public String toString() {
        return "TaskLifecycle{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", dayFromId=" + dayFromId +
                ", dayToId=" + dayToId +
                ", localId=" + localId +
                '}';
    }

    public long getLocalId() {
        return localId;
    }

    public void setLocalId(long localId) {
        this.localId = localId;
    }

    @Transient
    private long localId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskLifecycle that = (TaskLifecycle) o;

        if (id != that.id) return false;
        if (taskId != that.taskId) return false;
        if (dayFromId != that.dayFromId) return false;
        if (dayToId != that.dayToId) return false;
        return localId == that.localId;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (taskId ^ (taskId >>> 32));
        result = 31 * result + (int) (dayFromId ^ (dayFromId >>> 32));
        result = 31 * result + (int) (dayToId ^ (dayToId >>> 32));
        result = 31 * result + (int) (localId ^ (localId >>> 32));
        return result;
    }
}


