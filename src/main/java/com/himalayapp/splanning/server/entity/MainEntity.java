package com.himalayapp.splanning.server.entity;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainEntity {
    public HashSet<Day> dayList;
    public HashSet<DeletedTask> deletedTasksList;
    public HashSet<DoneGoal> doneGoalList;
    public HashSet<DoneTask> doneTaskList;
    public HashSet<Goal> dtoGoalList;
    public HashSet<Task> dtoTaskList;
    public HashSet<InProgressGoal> inProgressGoalList;
    public HashSet<InProgressTask> inProgressTaskList;
    public HashSet<MonthRepeating> monthRepeatingList;
    public HashSet<Notification> notificationList;
    public HashSet<Reminding> remindingList;
    public HashSet<Repeating> repeatingList;
    public HashSet<TaskLifecycle> taskLifecycleList;
    public HashSet<TaskToGoal> taskToGoalList;
    public HashMap<Long, Integer> deletedItems;
    public MainEntity dtoWithLocalIds;

    public MainEntity() {
        dayList = new HashSet<Day>();
        deletedTasksList = new HashSet<DeletedTask>();
        doneGoalList = new HashSet<DoneGoal>();
        doneTaskList = new HashSet<DoneTask>();
        dtoGoalList = new HashSet<Goal>();
        dtoTaskList = new HashSet<Task>();
        inProgressGoalList = new HashSet<InProgressGoal>();
        inProgressTaskList = new HashSet<InProgressTask>();
        monthRepeatingList = new HashSet<MonthRepeating>();
        notificationList = new HashSet<Notification>();
        remindingList = new HashSet<Reminding>();
        repeatingList = new HashSet<Repeating>();
        taskLifecycleList = new HashSet<TaskLifecycle>();
        taskToGoalList = new HashSet<TaskToGoal>();
        deletedItems = new HashMap<Long, Integer>();
        dtoWithLocalIds = new MainEntity();
    }

    public HashSet<Day> getDayList() {
        return dayList;
    }

    public void setDayList(HashSet<Day> dayList) {
        this.dayList = dayList;
    }

    public HashSet<DeletedTask> getDeletedTasksList() {
        return deletedTasksList;
    }

    public void setDeletedTasksList(HashSet<DeletedTask> deletedTasksList) {
        this.deletedTasksList = deletedTasksList;
    }

    public HashSet<DoneGoal> getDoneGoalList() {
        return doneGoalList;
    }

    public void setDoneGoalList(HashSet<DoneGoal> doneGoalList) {
        this.doneGoalList = doneGoalList;
    }

    public HashSet<DoneTask> getDoneTaskList() {
        return doneTaskList;
    }

    public void setDoneTaskList(HashSet<DoneTask> doneTaskList) {
        this.doneTaskList = doneTaskList;
    }

    public HashSet<Goal> getDtoGoalList() {
        return dtoGoalList;
    }

    public void setDtoGoalList(HashSet<Goal> dtoGoalList) {
        this.dtoGoalList = dtoGoalList;
    }

    public HashSet<Task> getDtoTaskList() {
        return dtoTaskList;
    }

    public void setDtoTaskList(HashSet<Task> dtoTaskList) {
        this.dtoTaskList = dtoTaskList;
    }

    public HashSet<InProgressGoal> getInProgressGoalList() {
        return inProgressGoalList;
    }

    public void setInProgressGoalList(HashSet<InProgressGoal> inProgressGoalList) {
        this.inProgressGoalList = inProgressGoalList;
    }

    public HashSet<InProgressTask> getInProgressTaskList() {
        return inProgressTaskList;
    }

    public void setInProgressTaskList(HashSet<InProgressTask> inProgressTaskList) {
        this.inProgressTaskList = inProgressTaskList;
    }

    public HashSet<MonthRepeating> getMonthRepeatingList() {
        return monthRepeatingList;
    }

    public void setMonthRepeatingList(HashSet<MonthRepeating> monthRepeatingList) {
        this.monthRepeatingList = monthRepeatingList;
    }

    public HashSet<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(HashSet<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public HashSet<Reminding> getRemindingList() {
        return remindingList;
    }

    public void setRemindingList(HashSet<Reminding> remindingList) {
        this.remindingList = remindingList;
    }

    public HashSet<Repeating> getRepeatingList() {
        return repeatingList;
    }

    public void setRepeatingList(HashSet<Repeating> repeatingList) {
        this.repeatingList = repeatingList;
    }

    public HashSet<TaskLifecycle> getTaskLifecycleList() {
        return taskLifecycleList;
    }

    public void setTaskLifecycleList(HashSet<TaskLifecycle> taskLifecycleList) {
        this.taskLifecycleList = taskLifecycleList;
    }

    public HashSet<TaskToGoal> getTaskToGoalList() {
        return taskToGoalList;
    }

    public void setTaskToGoalList(HashSet<TaskToGoal> taskToGoalList) {
        this.taskToGoalList = taskToGoalList;
    }

    public HashMap<Long, Integer> getDeletedItems() {
        return deletedItems;
    }

    public void setDeletedItems(HashMap<Long, Integer> deletedItems) {
        this.deletedItems = deletedItems;
    }

    @Override
    public String toString() {
        return "MainEntity{" +
                "dayList=" + dayList +
                ", deletedTasksList=" + deletedTasksList +
                ", doneGoalList=" + doneGoalList +
                ", doneTaskList=" + doneTaskList +
                ", dtoGoalList=" + dtoGoalList +
                ", dtoTaskList=" + dtoTaskList +
                ", inProgressGoalList=" + inProgressGoalList +
                ", inProgressTaskList=" + inProgressTaskList +
                ", monthRepeatingList=" + monthRepeatingList +
                ", notificationList=" + notificationList +
                ", remindingList=" + remindingList +
                ", repeatingList=" + repeatingList +
                ", taskLifecycleList=" + taskLifecycleList +
                ", taskToGoalList=" + taskToGoalList +
                ", deletedItems=" + deletedItems +
                '}';
    }
}
