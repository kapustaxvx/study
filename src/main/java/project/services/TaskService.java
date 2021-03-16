package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.exceptions.TaskNotFoundException;
import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.Task;
import project.models.TaskData;

import java.util.List;

@Component
public class TaskService {
    @Autowired
    private Model model;

    public Task createTask(long userId, TaskData taskData) throws UserNotFoundException {
        return model.createTask(userId, taskData);
    }

    public Task getTaskById(long taskId) throws TaskNotFoundException {
        return model.getTaskById(taskId);
    }

    public Task updateTask(long taskId, TaskData taskData) throws Exception {
        return model.updateTask(taskId, taskData);
    }

    public void deleteTask(long taskId) throws TaskNotFoundException {
        try {
            model.deleteTask(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException {
        return model.getAllTasksOfUser(userId);
    }

    public List<Task> getAllActiveTaskOfUser(long userId) throws UserNotFoundException {
        return model.getAllActiveTaskOfUser(userId);
    }


}
