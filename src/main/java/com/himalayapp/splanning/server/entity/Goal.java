package com.himalayapp.splanning.server.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Goal")
public class Goal {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "text", nullable = false)
    private String goalText;

    @Column(name = "notice", nullable = false)
    private String notice;

    @Column(name = "deadline", nullable = false)
    private long deadline;

    @Transient
    private long localId;

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", goalText='" + goalText + '\'' +
                ", notice='" + notice + '\'' +
                ", deadline=" + deadline +
                ", localId=" + localId +
                '}';
    }

    public long getLocalId() {
        return localId;
    }

    public void setLocalId(long localId) {
        this.localId = localId;
    }

    public Goal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoalText() {
        return goalText;
    }

    public void setGoalText(String goalText) {
        this.goalText = goalText;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal = (Goal) o;

        if (id != goal.id) return false;
        if (deadline != goal.deadline) return false;
        if (localId != goal.localId) return false;
        if (goalText != null ? !goalText.equals(goal.goalText) : goal.goalText != null) return false;
        return notice != null ? notice.equals(goal.notice) : goal.notice == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (goalText != null ? goalText.hashCode() : 0);
        result = 31 * result + (notice != null ? notice.hashCode() : 0);
        result = 31 * result + (int) (deadline ^ (deadline >>> 32));
        result = 31 * result + (int) (localId ^ (localId >>> 32));
        return result;
    }
}

