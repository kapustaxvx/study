package project.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.config.DataStorageProperties;
import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;
import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;
import project.services.TaskService;
import project.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private DataStorageProperties dataStorageProperties;

    @GetMapping("/data_storage")
    String getActiveDataStorage(){
        return dataStorageProperties.getActive().name();
    }

    @PostMapping(value = "/user")
    User createUser(@RequestBody UserData user) throws UserExistsException {
        logger.info("Received user: {}", user.toString());
        return userService.createUser(user);
    }

    @PostMapping(value = "user/{userId}/task")
    Task createTask(@PathVariable(value = "userId") Long userId, @RequestBody TaskData task) throws UserNotFoundException {
        return taskService.createTask(userId, task);
    }

    @GetMapping(value = "/task/{id}")
    Task getTaskById(@PathVariable(value = "id") Long id) throws TaskNotFoundException {
        logger.debug("Received task: {}", id);
        return taskService.getTaskById(id);
    }

    @GetMapping(value = "user/{id}")
    User getUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }

    @PutMapping(value = "/task/{taskId}")
    Task updateTask(@PathVariable(value = "taskId") Long taskId,
                    @RequestBody TaskData taskData) throws Exception {
        return taskService.updateTask(taskId, taskData);
    }

    @DeleteMapping(value = "/task/{id}")
    void deleteTask(@PathVariable(value = "id") Long taskId) throws TaskNotFoundException {
        taskService.deleteTask(taskId);
    }

    @DeleteMapping(value = "user/{id}")
    void deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        userService.deleteUser(userId);
    }

    @GetMapping(value = "user/{userId}/tasks")
    List<Task> getAllTasksOfUser(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        return taskService.getAllTasksOfUser(userId);
    }

    @GetMapping(value = "user/{userId}/tasks/active")
    List<Task> getAllActiveTaskOfUser(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        return taskService.getAllActiveTaskOfUser(userId);
    }


}
