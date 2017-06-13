package com.himalayapp.splanning.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "text", nullable = false)
    private String taskText;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Transient
    private long localId;

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskText='" + taskText + '\'' +
                ", priority=" + priority +
                ", localId=" + localId +
                '}';
    }

    public long getLocalId() {
        return localId;
    }

    public void setLocalId(long localId) {
        this.localId = localId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (priority != task.priority) return false;
        if (localId != task.localId) return false;
        return taskText != null ? taskText.equals(task.taskText) : task.taskText == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (taskText != null ? taskText.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + (int) (localId ^ (localId >>> 32));
        return result;
    }
}
