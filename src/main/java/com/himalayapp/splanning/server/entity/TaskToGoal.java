package com.himalayapp.splanning.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TaskToGoal")
public class TaskToGoal {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "task_id", nullable = false)
    private long taskId;

    @Column(name = "goal_id", nullable = false)
    private long goalId;

    @Transient
    private long localId;

    @Override
    public String toString() {
        return "TaskToGoal{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", goalId=" + goalId +
                ", localId=" + localId +
                '}';
    }

    public long getLocalId() {
        return localId;
    }

    public void setLocalId(long localId) {
        this.localId = localId;
    }

    public TaskToGoal() {
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

    public long getGoalId() {
        return goalId;
    }

    public void setGoalId(long goalId) {
        this.goalId = goalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskToGoal that = (TaskToGoal) o;

        if (id != that.id) return false;
        if (taskId != that.taskId) return false;
        if (goalId != that.goalId) return false;
        return localId == that.localId;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (taskId ^ (taskId >>> 32));
        result = 31 * result + (int) (goalId ^ (goalId >>> 32));
        result = 31 * result + (int) (localId ^ (localId >>> 32));
        return result;
    }
}