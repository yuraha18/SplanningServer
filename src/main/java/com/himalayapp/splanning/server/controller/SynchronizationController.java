package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.URL;
import com.himalayapp.splanning.server.entity.*;

import com.himalayapp.splanning.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SynchronizationController {


    @Autowired private SynchronizerService synchServise;
    @Autowired private DaysService daysService;
    @Autowired private DeletedTasksService deletedTasksService;
    @Autowired private DoneGoalsService doneGoalsService;
    @Autowired private DoneTasksService doneTasksService;
    @Autowired private GoalsService goalsService;
    @Autowired private InProgressGoalsService inProgressGoalsService;
    @Autowired private InProgressTasksService inProgressTasksService;
    @Autowired private MonthRepeatingService monthRepeatingService;
    @Autowired private NotificationsService notificationsService;
    @Autowired private RemindingService remindingService;
    @Autowired private RepeatingService repeatingService;
    @Autowired private TaskService taskService;
    @Autowired private  TaskLifecycleService taskLifecycleService;
    @Autowired private TaskToGoalService taskToGoalService;

    @RequestMapping(value = URL.GET_SYNCH_DATA, method = RequestMethod.GET)
    @ResponseBody
    public MainEntity getSynchObjects(@RequestParam("userId") long userId, @RequestParam("time") long time) {
        List<SynchObject> list = synchServise.getObjectsForSynch(userId, time);

        return getEntitiesFromDbForSynch(list);
    }



    public  MainEntity getEntitiesFromDbForSynch(List<SynchObject> synchObjects)
    {

        MainEntity mainEntity = new MainEntity();

        try {
            for (SynchObject object : synchObjects) {
                int tableId = object.getTableId();
                long sid = object.getRowId();
                switch (tableId) {
                    case 1:mainEntity.dtoTaskList.add(getTask(sid));break;
                    case 2:mainEntity.remindingList.add(getReminding(sid));break;
                    case 3:
                        Day day = getDay(sid);
                        if (day!=null)mainEntity.dayList.add(day);break;
                    case 4:getGoal(sid, mainEntity);break;
                    case 5:getDoneTask(sid, mainEntity);break;
                    case 6:mainEntity.inProgressTaskList.add(getInProgressTask(sid));break;
                    case 7:getRepeating(sid, mainEntity);break;
                    case 8:getMonthRepeating(sid, mainEntity);break;
                    case 9:getInProgressGoal(sid, mainEntity);break;
                    case 10:getTaskToGoal(sid, mainEntity);break;
                    case 11:mainEntity.doneGoalList.add(getDoneGoal(sid));break;
                    case 12:mainEntity.deletedTasksList.add(getDeletedTask(sid));break;
                    case 13:mainEntity.taskLifecycleList.add(getTaskLifecycle(sid));break;
                    case 14:getNotification(sid, mainEntity);break;
                }
            }
        }
        catch (Exception e){e.printStackTrace();}

        System.out.println(mainEntity.remindingList);
        System.out.println(mainEntity.remindingList.size());
        System.out.println("return list: " + mainEntity);
        return mainEntity;
    }

    private void getNotification(long sid, MainEntity mainEntity) {
        Notification notification =  notificationsService.getById(sid);
        if (notification==null)
            mainEntity.deletedItems.put(sid, Constants.dbTables.get(Constants.NOTIFICATIONS_TABLE));

        else
            mainEntity.notificationList.add(notification);
    }

    private TaskLifecycle getTaskLifecycle(long sid) {
        return taskLifecycleService.getById(sid);
    }

    private DeletedTask getDeletedTask(long sid) {
        return deletedTasksService.getById(sid);
    }

    private  DoneGoal getDoneGoal(long sid) {
        return doneGoalsService.getById(sid);
    }

    private  void getTaskToGoal(long sid, MainEntity mainEntity) {
        TaskToGoal taskToGoal =  taskToGoalService.getById(sid);
        if (taskToGoal==null)
            mainEntity.deletedItems.put(sid, Constants.dbTables.get(Constants.TASK_TO_GOAL_TABLE));

        else
            mainEntity.taskToGoalList.add(taskToGoal);

    }

    private void getInProgressGoal(long sid, MainEntity mainEntity) {
        InProgressGoal inProgressGoal =  inProgressGoalsService.getById(sid);
        if (inProgressGoal==null)
            mainEntity.deletedItems.put(sid, Constants.dbTables.get(Constants.IN_PROGRESS_GOALS_TABLE));

        else
            mainEntity.inProgressGoalList.add(inProgressGoal);

    }

    private void getMonthRepeating(long sid, MainEntity mainEntity) {
        MonthRepeating monthRepeating = monthRepeatingService.getById(sid);
        if (monthRepeating==null)
            mainEntity.deletedItems.put(sid, Constants.dbTables.get(Constants.MONTH_REPEATING_TABLE));

        else
            mainEntity.monthRepeatingList.add(monthRepeating);

    }

    private void getRepeating(long sid, MainEntity mainEntity) {
        Repeating repeating =  repeatingService.getById(sid);
        if (repeating==null)
            mainEntity.deletedItems.put(sid, Constants.dbTables.get(Constants.REPEATING_TABLE));

        else
            mainEntity.repeatingList.add(repeating);

    }

    private InProgressTask getInProgressTask(long sid) {
        return inProgressTasksService.getById(sid);
    }

    private void getDoneTask(long sid, MainEntity mainEntity) {
        DoneTask doneTask =  doneTasksService.getById(sid);
        System.out.println(doneTask +" dt");
        if (doneTask==null)
            mainEntity.deletedItems.put(sid, Constants.dbTables.get(Constants.DONE_TASKS_TABLE));

        else
            mainEntity.doneTaskList.add(doneTask);

    }

    private void getGoal(long sid, MainEntity mainEntity) {
        Goal goal = goalsService.getById(sid);
        if (goal==null)
            mainEntity.deletedItems.put(sid, Constants.dbTables.get(Constants.GOALS_TABLE));

        else
            mainEntity.dtoGoalList.add(goal);

    }

    private Day getDay(long sid) {
        return daysService.getById(sid);
    }

    private Reminding getReminding(long sid) {
        return remindingService.getById(sid);
    }

    private  Task getTask(long sid) {
        return taskService.getById(sid);
    }
}
