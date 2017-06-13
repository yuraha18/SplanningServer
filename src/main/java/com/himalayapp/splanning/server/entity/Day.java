package com.himalayapp.splanning.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Day")
public class Day {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Transient
    private long localId;


    public Day() {
    }

    public Day(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", localId=" + localId +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

        Day day = (Day) o;

        if (id != day.id) return false;
        if (localId != day.localId) return false;
        return text.equals(day.text);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + text.hashCode();
        result = 31 * result + (int) (localId ^ (localId >>> 32));
        return result;
    }
}



