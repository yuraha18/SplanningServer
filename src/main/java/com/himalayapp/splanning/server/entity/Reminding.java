package com.himalayapp.splanning.server.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Reminding")
public class Reminding {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "task_id", nullable = false)
    private long taskId;

    @Column(name = "time", nullable = false)
    private String time;

    @Transient
    private long localId;

    @Override
    public String toString() {
        return "Reminding{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", time='" + time + '\'' +
                ", localId=" + localId +
                '}';
    }

    public long getLocalId() {
        return localId;
    }

    public void setLocalId(long localId) {
        this.localId = localId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reminding reminding = (Reminding) o;

        if (id != reminding.id) return false;
        if (taskId != reminding.taskId) return false;
        if (localId != reminding.localId) return false;
        return time != null ? time.equals(reminding.time) : reminding.time == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (taskId ^ (taskId >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (int) (localId ^ (localId >>> 32));
        return result;
    }
}

