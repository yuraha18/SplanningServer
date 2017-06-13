package com.himalayapp.splanning.server.service.impl;


import com.himalayapp.splanning.server.entity.*;
import com.himalayapp.splanning.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private DaysService daysService;

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
    @Autowired private TaskLifecycleService taskLifecycleService;
    @Autowired private TaskToGoalService taskToGoalService;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public MainEntity synchWithApp(MainEntity mainEntity, long userId) {
        MainEntity responseEntity = null;
        try {
            responseEntity = new MainEntity();
            responseEntity.dtoWithLocalIds = setLocalDtos(mainEntity, userId);
            responseEntity = setWithGlobalDtos(responseEntity, mainEntity, userId);
            responseEntity.deletedItems = handleDeletedItems(mainEntity.deletedItems, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    private MainEntity setWithGlobalDtos(MainEntity responseEntity, MainEntity mainEntity, long userId) {

        if (!mainEntity.getDtoTaskList().isEmpty())
          responseEntity.setDtoTaskList(handleTaskList(mainEntity.getDtoTaskList(), userId));

        if (!mainEntity.getDayList().isEmpty())
          responseEntity.setDayList(handleDaysList(mainEntity.getDayList(), userId));

        if (!mainEntity.getDtoGoalList().isEmpty())
          responseEntity.setDtoGoalList(handleGoalsList(mainEntity.getDtoGoalList(), userId));

        if (!mainEntity.getDeletedTasksList().isEmpty())
          responseEntity.setDeletedTasksList(handleDeletedTasksList(mainEntity.getDeletedTasksList(), userId, mainEntity));

        if (!mainEntity.getDoneGoalList().isEmpty())
          responseEntity.setDoneGoalList(handleDoneGoalsList(mainEntity.getDoneGoalList(), userId, mainEntity));

        if (!mainEntity.getDoneTaskList().isEmpty())
          responseEntity.setDoneTaskList(handleDoneTaskList(mainEntity.getDoneTaskList(), userId, mainEntity));

        if (!mainEntity.getInProgressGoalList().isEmpty())
          responseEntity.setInProgressGoalList(handleInProgressGoalList(mainEntity.getInProgressGoalList(), userId, mainEntity));

        if (!mainEntity.getInProgressTaskList().isEmpty())
          responseEntity.setInProgressTaskList(handleInProgressTaskList(mainEntity.getInProgressTaskList(), userId, mainEntity));

        if (!mainEntity.getMonthRepeatingList().isEmpty())
          responseEntity.setMonthRepeatingList(handleMonthRepeatingList(mainEntity.getMonthRepeatingList(), userId, mainEntity));

        if (!mainEntity.getNotificationList().isEmpty())
          responseEntity.setNotificationList(handleNotificationList(mainEntity.getNotificationList(), userId, mainEntity));

        if (!mainEntity.getRemindingList().isEmpty())
          responseEntity.setRemindingList(handleRemindingList(mainEntity.getRemindingList(), userId));

        if (!mainEntity.getRepeatingList().isEmpty())
          responseEntity.setRepeatingList(handleRepeatingList(mainEntity.getRepeatingList(), userId, mainEntity));

        if (!mainEntity.getTaskLifecycleList().isEmpty())
          responseEntity.setTaskLifecycleList(handleTaskLifecycleList(mainEntity.getTaskLifecycleList(), userId, mainEntity));

        if (!mainEntity.getTaskToGoalList().isEmpty())
        responseEntity.setTaskToGoalList(handleTaskToGoalList(mainEntity.getTaskToGoalList(), userId, mainEntity));

        return responseEntity;
    }


    private HashMap<Long, Integer> handleDeletedItems(HashMap<Long, Integer> deletedItems, long userId) {
        HashMap<Long, Integer> resultMap = new HashMap<Long, Integer>();

        for (Map.Entry<Long, Integer> entry : deletedItems.entrySet()) {
            try {
                int tableId = entry.getValue();
                long sid = entry.getKey();
                switch (tableId) {
                    case 4:
                        goalsService.remove(sid, userId);
                        break;
                    case 5:
                        doneTasksService.remove(sid, userId);
                        break;
                    case 7:
                        repeatingService.remove(sid, userId);
                        break;
                    case 8:
                        monthRepeatingService.remove(sid, userId);
                        break;
                    case 9:
                        inProgressGoalsService.remove(sid, userId);
                        break;
                    case 10:
                        taskToGoalService.remove(sid, userId);
                        break;
                    case 14:
                        notificationsService.remove(sid, userId);
                        break;
                }
                resultMap.put(entry.getKey(), entry.getValue());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return resultMap;
    }


    private long getTaskSid(long taskId, MainEntity mainEntity) {
        HashSet<Task> taskList = mainEntity.getDtoTaskList();

        for (Task task : taskList) {
            if (task.getLocalId() == taskId)
                return task.getId();
        }
        return 0;
    }

    private long getGoalSid(long goalId, MainEntity mainEntity) {
        HashSet<Goal> goalList = mainEntity.getDtoGoalList();

        for (Goal goal : goalList) {
            if (goal.getLocalId() == goalId)
                return goal.getId();
        }
        return 0;
    }

    private long getDaySid(long dayId, MainEntity mainEntity) {
        HashSet<Day> dayList = mainEntity.getDayList();
        System.out.println("dayId: " + dayId);
        for (Day day : dayList) {
            if (day.getLocalId() == dayId)
                return day.getId();
        }
        return 0;
    }


    private HashSet<Reminding> handleRemindingList(HashSet<Reminding> remindingList, long userId) {
        HashSet<Reminding> newEntityList = new HashSet<Reminding>();
        for (Reminding reminding : remindingList)
            newEntityList.add(saveReminding(reminding, userId));

        return newEntityList;
    }

    private Reminding saveReminding(Reminding reminding, long userId) {
        Reminding newEntity = remindingService.save(reminding, userId);
        newEntity.setLocalId(reminding.getLocalId());
        return newEntity;
    }


    private HashSet<Notification> handleNotificationList(HashSet<Notification> notificationList, long userId, MainEntity mainEntity) {
        HashSet<Notification> newEntityList = new HashSet<Notification>();
        for (Notification notification : notificationList) {
            notification.setDayId(getDaySid(notification.getDayId(), mainEntity));
            notification.setTaskId(getTaskSid(notification.getTaskId(), mainEntity));

            newEntityList.add(saveNotification(notification, userId));

        }

        return newEntityList;
    }

    private Notification saveNotification(Notification notification, long userId) {
        Notification newEntity = notificationsService.save(notification, userId);
        newEntity.setLocalId(notification.getLocalId());
        return newEntity;
    }

    private HashSet<MonthRepeating> handleMonthRepeatingList(HashSet<MonthRepeating> monthRepeatingList, long userId, MainEntity mainEntity) {
        HashSet<MonthRepeating> newEntityList = new HashSet<MonthRepeating>();
        for (MonthRepeating monthRepeating : monthRepeatingList) {
            monthRepeating.setTaskId(getTaskSid(monthRepeating.getTaskId(), mainEntity));

            newEntityList.add(saveMonthRepeating(monthRepeating, userId));

        }

        return newEntityList;
    }

    private MonthRepeating saveMonthRepeating(MonthRepeating monthRepeating, long userId) {
        MonthRepeating newEntity = monthRepeatingService.save(monthRepeating, userId);
        newEntity.setLocalId(monthRepeating.getLocalId());
        return newEntity;
    }

    private HashSet<InProgressGoal> handleInProgressGoalList(HashSet<InProgressGoal> inProgressGoalList, long userId, MainEntity mainEntity) {
        HashSet<InProgressGoal> newEntityList = new HashSet<InProgressGoal>();
        for (InProgressGoal inProgressGoal : inProgressGoalList) {
            inProgressGoal.setGoalId(getGoalSid(inProgressGoal.getGoalId(), mainEntity));

            newEntityList.add(saveInProgressGoal(inProgressGoal, userId));
        }

        return newEntityList;
    }

    private InProgressGoal saveInProgressGoal(InProgressGoal inProgressGoal, long userId) {
        InProgressGoal newEntity = inProgressGoalsService.save(inProgressGoal, userId);
        newEntity.setLocalId(inProgressGoal.getLocalId());

        return newEntity;
    }

    private HashSet<InProgressTask> handleInProgressTaskList(HashSet<InProgressTask> inProgressTaskList, long userId, MainEntity mainEntity) {
        HashSet<InProgressTask> newEntityList = new HashSet<InProgressTask>();
        for (InProgressTask inProgressTask : inProgressTaskList) {
            inProgressTask.setTaskId(getTaskSid(inProgressTask.getTaskId(), mainEntity));
            newEntityList.add(saveInProgressTask(inProgressTask, userId));
        }

        return newEntityList;
    }

    private InProgressTask saveInProgressTask(InProgressTask inProgressTask, long userId) {
        InProgressTask newEntity = inProgressTasksService.save(inProgressTask, userId);
        newEntity.setLocalId(inProgressTask.getLocalId());

        return newEntity;
    }

    private HashSet<Goal> handleGoalsList(HashSet<Goal> dtoGoalList, long userId) {
        HashSet<Goal> newEntityList = new HashSet<Goal>();
        for (Goal goal : dtoGoalList) {
            newEntityList.add(saveGoal(goal, userId));
        }

        return newEntityList;
    }

    private Goal saveGoal(Goal goal, long userId) {
        Goal newEntity = goalsService.save(goal, userId);
        newEntity.setLocalId(goal.getLocalId());

        return newEntity;
    }

    private HashSet<DoneTask> handleDoneTaskList(HashSet<DoneTask> doneTaskList, long userId, MainEntity mainEntity) {
        HashSet<DoneTask> newEntityList = new HashSet<DoneTask>();
        for (DoneTask doneTask : doneTaskList) {
            doneTask.setDayId(getDaySid(doneTask.getDayId(), mainEntity));
            doneTask.setTaskId(getTaskSid(doneTask.getTaskId(), mainEntity));
            newEntityList.add(saveDoneTask(doneTask, userId));
        }

        return newEntityList;
    }

    private DoneTask saveDoneTask(DoneTask doneTask, long userId) {
        DoneTask newEntity = doneTasksService.save(doneTask, userId);
        newEntity.setLocalId(doneTask.getLocalId());

        return newEntity;
    }

    private HashSet<DoneGoal> handleDoneGoalsList(HashSet<DoneGoal> doneGoalList, long userId, MainEntity mainEntity) {
        HashSet<DoneGoal> newEntityList = new HashSet<DoneGoal>();
        for (DoneGoal doneGoal : doneGoalList) {
            doneGoal.setDayId(getDaySid(doneGoal.getDayId(), mainEntity));
            doneGoal.setGoalId(getGoalSid(doneGoal.getGoalId(), mainEntity));

            newEntityList.add(saveDoneGoal(doneGoal, userId));
        }

        return newEntityList;
    }

    private DoneGoal saveDoneGoal(DoneGoal doneGoal, long userId) {
        DoneGoal newEntity = doneGoalsService.save(doneGoal, userId);
        newEntity.setLocalId(doneGoal.getLocalId());

        return newEntity;
    }

    private HashSet<DeletedTask> handleDeletedTasksList(HashSet<DeletedTask> deletedTasksList, long userId, MainEntity mainEntity) {
        HashSet<DeletedTask> newEntityList = new HashSet<DeletedTask>();
        for (DeletedTask deletedTask : deletedTasksList) {
            deletedTask.setDayId(getDaySid(deletedTask.getDayId(), mainEntity));
            deletedTask.setTaskId(getTaskSid(deletedTask.getTaskId(), mainEntity));
            newEntityList.add(saveDeletedTask(deletedTask, userId));
        }

        return newEntityList;
    }

    private DeletedTask saveDeletedTask(DeletedTask deletedTask, long userId) {
        DeletedTask newEntity = deletedTasksService.save(deletedTask, userId);
        newEntity.setLocalId(deletedTask.getLocalId());

        return newEntity;
    }

    private HashSet<Day> handleDaysList(HashSet<Day> dayList, long userId) {
        HashSet<Day> newEntityList = new HashSet<Day>();
        for (Day day : dayList) {
            newEntityList.add(saveDay(day, userId));
        }
        return newEntityList;
    }

    private Day saveDay(Day day, long userId) {
        Day newEntity = daysService.save(day, userId);
        newEntity.setLocalId(day.getLocalId());

        return newEntity;
    }

    private HashSet<TaskToGoal> handleTaskToGoalList(HashSet<TaskToGoal> taskToGoalList, long userId, MainEntity mainEntity) {
        HashSet<TaskToGoal> newEntityList = new HashSet<TaskToGoal>();
        for (TaskToGoal taskToGoal : taskToGoalList) {
            taskToGoal.setTaskId(getTaskSid(taskToGoal.getTaskId(), mainEntity));
            taskToGoal.setGoalId(getGoalSid(taskToGoal.getGoalId(), mainEntity));
            newEntityList.add(saveTaskToGoal(taskToGoal, userId));
        }

        return newEntityList;
    }

    private TaskToGoal saveTaskToGoal(TaskToGoal taskToGoal, long userId) {
        TaskToGoal newEntity = taskToGoalService.save(taskToGoal, userId);
        newEntity.setLocalId(taskToGoal.getLocalId());

        return newEntity;
    }

    private HashSet<TaskLifecycle> handleTaskLifecycleList(HashSet<TaskLifecycle> taskLifecycleList, long userId, MainEntity mainEntity) {
        HashSet<TaskLifecycle> newEntityList = new HashSet<TaskLifecycle>();
        for (TaskLifecycle taskLifecycle : taskLifecycleList) {
            taskLifecycle.setDayFromId(getDaySid(taskLifecycle.getDayFromId(), mainEntity));
            taskLifecycle.setDayToId(getDaySid(taskLifecycle.getDayToId(), mainEntity));
            taskLifecycle.setTaskId(getTaskSid(taskLifecycle.getTaskId(), mainEntity));

            newEntityList.add(saveTaskLifecycle(taskLifecycle, userId));
        }

        return newEntityList;
    }

    private TaskLifecycle saveTaskLifecycle(TaskLifecycle taskLifecycle, long userId) {
        TaskLifecycle newEntity = taskLifecycleService.save(taskLifecycle, userId);
        newEntity.setLocalId(taskLifecycle.getLocalId());

        return newEntity;
    }

    public HashSet<Task> handleTaskList(HashSet<Task> dtoTaskList, long userId) {
        HashSet<Task> newEntityList = new HashSet<Task>();
        for (Task task : dtoTaskList) {
            Task newEntity = taskService.save(task, userId);
            newEntity.setLocalId(task.getLocalId());
            newEntityList.add(newEntity);
        }


        return newEntityList;
    }

    private HashSet<Repeating> handleRepeatingList(HashSet<Repeating> repeatingList, long userId, MainEntity mainEntity) {
        HashSet<Repeating> newEntityList = new HashSet<Repeating>();
        for (Repeating repeating : repeatingList) {
            repeating.setDayId(getDaySid(repeating.getDayId(), mainEntity));
            repeating.setTaskId(getTaskSid(repeating.getTaskId(), mainEntity));

            newEntityList.add(saveRepeating(repeating, userId));

        }

        return newEntityList;
    }

    private Repeating saveRepeating(Repeating repeating, long userId) {
        Repeating newEntity = repeatingService.save(repeating, userId);
        newEntity.setLocalId(repeating.getLocalId());

        return newEntity;
    }

    //////////////////////////////////////////////////////////////////////////////////
    private MainEntity setLocalDtos(MainEntity mainEntity, long userId) {
        MainEntity mainEntityWithLocalIds = new MainEntity();

        if (!mainEntity.dtoWithLocalIds.dtoGoalList.isEmpty())
          mainEntityWithLocalIds.setDtoGoalList(handleGoalWithLocalIds(mainEntity.dtoWithLocalIds.dtoGoalList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.deletedTasksList.isEmpty())
          mainEntityWithLocalIds.setDeletedTasksList(handleDeletedTasksWithLocalIds(mainEntity.dtoWithLocalIds.deletedTasksList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.doneGoalList.isEmpty())
          mainEntityWithLocalIds.setDoneGoalList(handleDoneGoalWithLocalIds(mainEntity.dtoWithLocalIds.doneGoalList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.doneTaskList.isEmpty())
          mainEntityWithLocalIds.setDoneTaskList(handleDoneTaskWithLocalIds(mainEntity.dtoWithLocalIds.doneTaskList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.inProgressGoalList.isEmpty())
          mainEntityWithLocalIds.setInProgressGoalList(handleInProgressGoalWithLocalIds(mainEntity.dtoWithLocalIds.inProgressGoalList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.inProgressTaskList.isEmpty())
          mainEntityWithLocalIds.setInProgressTaskList(handleInProgressTaskWithLocalIds(mainEntity.dtoWithLocalIds.inProgressTaskList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.monthRepeatingList.isEmpty())
          mainEntityWithLocalIds.setMonthRepeatingList(handleMonthRepeatingWithLocalIds(mainEntity.dtoWithLocalIds.monthRepeatingList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.notificationList.isEmpty())
          mainEntityWithLocalIds.setNotificationList(handleNotificationWithLocalIds(mainEntity.dtoWithLocalIds.notificationList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.remindingList.isEmpty())
          mainEntityWithLocalIds.setRemindingList(handleRemindingWithLocalIds(mainEntity.dtoWithLocalIds.remindingList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.repeatingList.isEmpty())
          mainEntityWithLocalIds.setRepeatingList(handleRepeatingWithLocalIds(mainEntity.dtoWithLocalIds.repeatingList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.taskLifecycleList.isEmpty())
          mainEntityWithLocalIds.setTaskLifecycleList(handleTaskLifecycleWithLocalIds(mainEntity.dtoWithLocalIds.taskLifecycleList, userId, mainEntity));

        if (!mainEntity.dtoWithLocalIds.taskToGoalList.isEmpty())
          mainEntityWithLocalIds.setTaskToGoalList(handleTaskToGoalWithLocalIds(mainEntity.dtoWithLocalIds.taskToGoalList, userId, mainEntity));

        return mainEntityWithLocalIds;
    }

    private  HashSet<TaskToGoal> handleTaskToGoalWithLocalIds(HashSet<TaskToGoal> taskToGoalList, long userId, MainEntity mainEntity) {
        HashSet<TaskToGoal> entitySet = new HashSet<TaskToGoal>();
        for (TaskToGoal entity : taskToGoalList) {
            TaskToGoal newEntity = handleTaskToGoal(entity, mainEntity);
            entitySet.add(saveTaskToGoal(newEntity, userId));
        }

        return entitySet;
    }

    private TaskToGoal handleTaskToGoal(TaskToGoal entity, MainEntity mainEntity) {
      long localGoalId = entity.getGoalId();
      long localTaskId =  entity.getTaskId();
      long goalSid = getGoalSid(localGoalId, mainEntity);
      long taskSid = getTaskSid(localTaskId, mainEntity);

      if (taskSid==0 && goalSid==0)
          throw new RuntimeException();

      if (taskSid!=0)
          entity.setTaskId(taskSid);

      if (goalSid!=0)
          entity.setGoalId(goalSid);

      return entity;
    }

    private HashSet<TaskLifecycle> handleTaskLifecycleWithLocalIds(HashSet<TaskLifecycle> taskLifecycleList, long userId, MainEntity mainEntity) {
        HashSet<TaskLifecycle> entitySet = new HashSet<TaskLifecycle>();
        for (TaskLifecycle entity : taskLifecycleList) {
            TaskLifecycle newEntity = handleTaskLifecycle(entity, mainEntity);
            entitySet.add(saveTaskLifecycle(newEntity, userId));
        }

        return entitySet;
    }

    private TaskLifecycle handleTaskLifecycle(TaskLifecycle entity, MainEntity mainEntity) {
      long localDayFromId =  entity.getDayFromId();
      long localDayToId =  entity.getDayToId();
      long localTaskId =  entity.getTaskId();
      long taskSid = getTaskSid(localTaskId, mainEntity);
      long dayFromSid = getDaySid(localDayFromId, mainEntity);
      long dayToSid = getDaySid(localDayToId, mainEntity);

      if (taskSid==0 && dayFromSid==0 && dayToSid==0)
          throw new RuntimeException();

      if (taskSid!=0)
          entity.setTaskId(taskSid);

      if (dayFromSid!=0)
          entity.setDayFromId(dayFromSid);

      if (dayToSid!=0)
          entity.setDayToId(dayToSid);

      return entity;
    }

    private HashSet<Repeating> handleRepeatingWithLocalIds(HashSet<Repeating> repeatingList, long userId, MainEntity mainEntity) {
        HashSet<Repeating> entitySet = new HashSet<Repeating>();
        for (Repeating entity : repeatingList) {
            Repeating newEntity = handleRepeating(entity, mainEntity);
            entitySet.add(saveRepeating(newEntity, userId));
        }

        return entitySet;
    }

    private Repeating handleRepeating(Repeating entity, MainEntity mainEntity) {
      long localDayId =  entity.getDayId();
       long localTaskId = entity.getTaskId();
        long taskSid = getTaskSid(localTaskId, mainEntity);
        long daySid = getDaySid(localDayId, mainEntity);

        if (taskSid==0 && daySid ==0)
            throw new RuntimeException();

        if (taskSid!=0)
            entity.setTaskId(taskSid);

        if (daySid!=0)
            entity.setDayId(daySid);

        return entity;
    }

    private  HashSet<Reminding> handleRemindingWithLocalIds(HashSet<Reminding> remindingList, long userId, MainEntity mainEntity) {
        HashSet<Reminding> entitySet = new HashSet<Reminding>();
        for (Reminding entity : remindingList) {
            Reminding newEntity = handleReminding(entity, mainEntity);
            entitySet.add(saveReminding(newEntity, userId));
        }

        return entitySet;
    }

    private Reminding handleReminding(Reminding entity, MainEntity mainEntity) {
     long localTaskId =   entity.getTaskId();
     long taskSid = getTaskSid(localTaskId, mainEntity);

     if (taskSid==0)
         throw new RuntimeException();

     entity.setTaskId(taskSid);

     return entity;
    }

    private HashSet<Notification> handleNotificationWithLocalIds(HashSet<Notification> notificationList, long userId, MainEntity mainEntity) {
        HashSet<Notification> entitySet = new HashSet<Notification>();
        for (Notification entity : notificationList) {
            Notification newEntity = handleNotification(entity, mainEntity);
            entitySet.add(saveNotification(newEntity, userId));
        }

        return entitySet;
    }

    private Notification handleNotification(Notification entity, MainEntity mainEntity) {
        long localTaskId  = entity.getTaskId();
        long localDayId = entity.getDayId();

        long taskSid = getTaskSid(localTaskId, mainEntity);
        long daySid = getDaySid(localDayId, mainEntity);

        if (taskSid==0 && daySid ==0)
            throw new RuntimeException();

        if (daySid!=0)
        entity.setDayId(daySid);

        if (taskSid!=0)
        entity.setTaskId(taskSid);

        return entity;
    }

    private  HashSet<MonthRepeating>  handleMonthRepeatingWithLocalIds(HashSet<MonthRepeating> monthRepeatingList, long userId, MainEntity mainEntity) {
        HashSet<MonthRepeating> entitySet = new HashSet<MonthRepeating>();
        for (MonthRepeating entity : monthRepeatingList) {
            MonthRepeating newEntity = handleMonthRepeating(entity, mainEntity);
            entitySet.add(saveMonthRepeating(newEntity, userId));
        }

        return entitySet;
    }

    private MonthRepeating handleMonthRepeating(MonthRepeating entity, MainEntity mainEntity) {
      long localTaskId =  entity.getTaskId();
      long taskSid = getTaskSid(localTaskId, mainEntity);

      if (taskSid==0)
          throw new RuntimeException();

      entity.setTaskId(localTaskId);

      return entity;
    }

    private HashSet<InProgressTask> handleInProgressTaskWithLocalIds(HashSet<InProgressTask> inProgressTaskList, long userId, MainEntity mainEntity) {
        HashSet<InProgressTask> entitySet = new HashSet<InProgressTask>();
        for (InProgressTask entity : inProgressTaskList) {
            InProgressTask newEntity = handleInProgressTask(entity, mainEntity);
            entitySet.add(saveInProgressTask(newEntity, userId));
        }

        return entitySet;
    }

    private InProgressTask handleInProgressTask(InProgressTask entity, MainEntity mainEntity) {
       long localTaskId = entity.getTaskId();
       long taskSid = getTaskSid(localTaskId, mainEntity);

       if (taskSid==0)
           throw new RuntimeException();

       entity.setTaskId(taskSid);

       return entity;
    }

    private  HashSet<InProgressGoal> handleInProgressGoalWithLocalIds(HashSet<InProgressGoal> inProgressGoalList, long userId, MainEntity mainEntity) {
        HashSet<InProgressGoal> entitySet = new HashSet<InProgressGoal>();
        for (InProgressGoal entity : inProgressGoalList) {
            InProgressGoal newEntity = handleInProgressGoal(entity, mainEntity);
            entitySet.add(saveInProgressGoal(newEntity, userId));
        }

        return entitySet;
    }

    private InProgressGoal handleInProgressGoal(InProgressGoal entity, MainEntity mainEntity) {
      long localGoalId = entity.getGoalId();
      long goalSid = getGoalSid(localGoalId, mainEntity);

      if (goalSid ==0)
          throw new RuntimeException();

      entity.setGoalId(goalSid);

      return entity;
    }

    private HashSet<DoneTask> handleDoneTaskWithLocalIds(HashSet<DoneTask> doneTaskList, long userId, MainEntity mainEntity) {
        HashSet<DoneTask> entitySet = new HashSet<DoneTask>();
        for (DoneTask entity : doneTaskList) {
            DoneTask newEntity = handleDoneTask(entity, mainEntity);
            entitySet.add(saveDoneTask(newEntity, userId));
        }

        return entitySet;
    }

    private DoneTask handleDoneTask(DoneTask entity, MainEntity mainEntity) {
      long localDayId = entity.getDayId();
      long localTaskId = entity.getTaskId();

      long taskSid = getTaskSid(localTaskId, mainEntity);
      long daySid = getDaySid(localDayId, mainEntity);

      if (taskSid ==0 && daySid ==0)
          throw new RuntimeException();

        if (daySid!=0)
      entity.setDayId(daySid);

        if (taskSid!=0)
      entity.setTaskId(taskSid);

      return entity;
    }

    private HashSet<DoneGoal>  handleDoneGoalWithLocalIds(HashSet<DoneGoal> doneGoalList, long userId, MainEntity mainEntity) {
        HashSet<DoneGoal> entitySet = new HashSet<DoneGoal>();
        for (DoneGoal entity : doneGoalList) {
           DoneGoal newEntity = handleDoneGoal(entity, mainEntity);
            entitySet.add(saveDoneGoal(newEntity, userId));
        }

        return entitySet;
    }

    private DoneGoal handleDoneGoal(DoneGoal entity, MainEntity mainEntity) {
      long localDayId = entity.getDayId();
      long localGoalId = entity.getGoalId();

      long daySid = getDaySid(localDayId, mainEntity);
      long goalSid = getGoalSid(localGoalId, mainEntity);

      if (daySid==0 && goalSid==0)
          throw new RuntimeException();

        if (daySid!=0)
      entity.setDayId(daySid);

        if (goalSid!=0)
      entity.setGoalId(goalSid);

      return entity;
    }

    private HashSet<DeletedTask> handleDeletedTasksWithLocalIds(HashSet<DeletedTask> deletedTasksList, long userId, MainEntity mainEntity) {
        HashSet<DeletedTask> entitySet = new HashSet<DeletedTask>();
        for (DeletedTask entity : deletedTasksList) {
          DeletedTask newEntity = handleDeletedTask(entity, mainEntity);
           entitySet.add(saveDeletedTask(newEntity, userId));
        }

        return entitySet;
    }

    private DeletedTask handleDeletedTask(DeletedTask entity, MainEntity mainEntity) {
        long localDayId = entity.getDayId();
        long localTaskId = entity.getTaskId();

        long daySid = getDaySid(localDayId, mainEntity);
        long taskSid = getTaskSid(localTaskId, mainEntity);

        if (daySid==0 && taskSid==0)
            throw new RuntimeException();

        if (daySid!=0)
          entity.setDayId(daySid);

        if (taskSid!=0)
        entity.setTaskId(taskSid);

        return entity;
    }

    private HashSet<Goal> handleGoalWithLocalIds(HashSet<Goal> dtoGoalList, long userId, MainEntity mainEntity) {

        HashSet<Goal> entitySet = new HashSet<Goal>();
        for (Goal goal : dtoGoalList) {
            long localDayId = goal.getDeadline();
            long daySid = getDaySid(localDayId, mainEntity);
            if (daySid == 0)
                throw new RuntimeException();

            goal.setDeadline(daySid);
            entitySet.add(saveGoal(goal, userId));
        }

        return entitySet;
    }

}
