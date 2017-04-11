package com.himalayapp.splanning.server;


import java.util.HashMap;

public class Constants {

    public static final HashMap<String, Integer> dbTables = new HashMap<String, Integer>();

    public static final String PROPERTIES_PATH = ".properties";
    public static final String LAST_UPDATE_TIME = "lastUpdateTime";
    public static final String USER_ID = "user_id" ;


    public static final String TASK_URL = "/tasks";
    public static final String DONE_TASKS_URL = "/donetasks";
    public static final String IN_PROGRESS_TASKS_URL = "/inprogresstasks";
    public static final String REPEATING_URL = "/repeating";
    public static final String MONTH_REPEATING_URL = "/monthrepeating";
    public static final String REMINDING_URL = "/reminding";
    public static final String GOALS_URL = "/goals";
    public static final String DONE_GOALS_URL = "/donegoals";
    public static final String IN_PROGRESS_GOALS_URL = "/inprogressgoals";
    public static final String TASK_TO_GOAL_URL = "/tasktogoal";
    public static final String DAYS_URL = "/days";
    public static final String DELETED_TASKS_URL = "/deletedtasks";
    public static final String TASK_LIFECYCLE_URL = "/tasklifecycle";
    public static final String NOTIFICATIONS_URL = "/notifications";
    public static final String SAVE_LIST_URL = "/savelist";

    public static final String TASK_TABLE = "Tasks";
    public static final String DONE_TASKS_TABLE = "DoneTasks";
    public static final String IN_PROGRESS_TASKS_TABLE = "InProgressTasks";
    public static final String REPEATING_TABLE = "Repeating";
    public static final String MONTH_REPEATING_TABLE = "MonthRepeating";
    public static final String REMINDING_TABLE = "Reminding";
    public static final String GOALS_TABLE = "Goals";
    public static final String DONE_GOALS_TABLE = "DoneGoals";
    public static final String IN_PROGRESS_GOALS_TABLE = "InProgressGoals";
    public static final String TASK_TO_GOAL_TABLE = "TaskToGoal";
    public static final String DAYS_TABLE = "Days";
    public static final String DELETED_TASKS_TABLE = "DeletedTasks";
    public static final String TASK_LIFECYCLE_TABLE = "TaskLifecycle";
    public static final String NOTIFICATIONS_TABLE = "Notifications";


    static  {
        dbTables.put(TASK_TABLE, 1);
        dbTables.put(REMINDING_TABLE, 2);
        dbTables.put(DAYS_TABLE, 3);
        dbTables.put(GOALS_TABLE, 4);
        dbTables.put(DONE_TASKS_TABLE, 5);
        dbTables.put(IN_PROGRESS_TASKS_TABLE, 6);
        dbTables.put(REPEATING_TABLE, 7);
        dbTables.put(MONTH_REPEATING_TABLE, 8);
        dbTables.put(IN_PROGRESS_GOALS_TABLE, 9);
        dbTables.put(TASK_TO_GOAL_TABLE, 10);
        dbTables.put(DONE_GOALS_TABLE, 11);
        dbTables.put(DELETED_TASKS_TABLE, 12);
        dbTables.put(TASK_LIFECYCLE_TABLE, 13);
        dbTables.put(NOTIFICATIONS_TABLE, 14);

    }
}
