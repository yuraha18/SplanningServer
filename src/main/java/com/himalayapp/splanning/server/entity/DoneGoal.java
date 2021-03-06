package com.himalayapp.splanning.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Table(name = "DoneGoal")
public class DoneGoal {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "goal_id", nullable = false)
    private long goalId;

    @Column(name = "day_id", nullable = false)
    private long dayId;

    @Transient
    private long localId;

    public long getLocalId() {
        return localId;
    }

    public void setLocalId(long localId) {
        this.localId = localId;
    }

    @Override
    public String toString() {
        return "DoneGoal{" +
                "id=" + id +
                ", goalId=" + goalId +
                ", dayId=" + dayId +
                ", localId=" + localId +
                '}';
    }

    public DoneGoal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGoalId() {
        return goalId;
    }

    public void setGoalId(long goalId) {
        this.goalId = goalId;
    }

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoneGoal doneGoal = (DoneGoal) o;

        if (id != doneGoal.id) return false;
        if (goalId != doneGoal.goalId) return false;
        if (dayId != doneGoal.dayId) return false;
        return localId == doneGoal.localId;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (goalId ^ (goalId >>> 32));
        result = 31 * result + (int) (dayId ^ (dayId >>> 32));
        result = 31 * result + (int) (localId ^ (localId >>> 32));
        return result;
    }
}

