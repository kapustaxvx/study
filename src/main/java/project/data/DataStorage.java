package project.data;

import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;

import java.util.List;
import java.util.Optional;


public interface DataStorage {
    Task addTask(long userId, TaskData task);

    Task updateTask(long taskId, TaskData taskData);

    void deleteTask(long taskId);

    List<Task> getAllActiveTask(long userId);

    List<Task> getAllTasks(long userId);

    User addUser(UserData user);

    User updateUser(long userId, UserData userData);

    void deleteUser(long userId);

    List<User> getAllUsers();

    Optional<Task> getTaskById(long taskId);

    Optional<User> getUser(long userId);
}
