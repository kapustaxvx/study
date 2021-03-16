package project.models;

import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;

import java.util.List;

/**
 * @author Ilia Moskalenko
 */
public interface Model {
    User createUser(UserData user) throws UserExistsException;

    Task createTask(long userId, TaskData task) throws UserNotFoundException;

    Task getTaskById(long taskId) throws TaskNotFoundException;

    User getUserById(long userId) throws UserNotFoundException;

    Task updateTask(long taskId, TaskData taskData) throws Exception;

    void deleteTask(long taskId) throws Exception;

    void deleteUser(long userId) throws Exception;

    List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException;

    List<Task> getAllActiveTaskOfUser(long userId) throws UserNotFoundException;
}
