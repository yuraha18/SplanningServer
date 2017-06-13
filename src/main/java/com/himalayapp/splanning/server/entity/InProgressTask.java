package com.himalayapp.splanning.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "InProgressTask")
public class InProgressTask {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "task_id", nullable = false)
    private long taskId;

    @Transient
    private long localId;

    @Override
    public String toString() {
        return "InProgressTask{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", localId=" + localId +
                '}';
    }

    public long getLocalId() {
        return localId;
    }

    public void setLocalId(long localId) {
        this.localId = localId;
    }

    public InProgressTask() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InProgressTask that = (InProgressTask) o;

        if (id != that.id) return false;
        if (taskId != that.taskId) return false;
        return localId == that.localId;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (taskId ^ (taskId >>> 32));
        result = 31 * result + (int) (localId ^ (localId >>> 32));
        return result;
    }
}

