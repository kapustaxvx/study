package project.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.data.DataStorage;
import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;
import project.locks.AutoCloseableReentrantLock;
import project.locks.LocksProvider;

import java.util.List;
import java.util.Objects;


@Component
public class ModelImpl implements Model {
    private static final Logger log = LoggerFactory.getLogger(ModelImpl.class);
    private DataStorage dataStorage;
    private LocksProvider locksProvider;
    private static volatile boolean sleep = true;

    @Autowired
    public ModelImpl(DataStorage dataStorage,
                     LocksProvider locksProvider) {
        this.dataStorage = dataStorage;
        this.locksProvider = locksProvider;
    }

    @Override
    public User createUser(UserData user) throws UserExistsException {
        boolean userNameExists = dataStorage.getAllUsers().stream()
                .anyMatch(user1 -> Objects.equals(user1.getName(), user.getName()));
        if (userNameExists) throw new UserExistsException();
        return dataStorage.addUser(user);
    }

    @Override
    public Task createTask(long userId, TaskData task) throws UserNotFoundException {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.addTask(userId, task);

    }

    @Override
    public Task getTaskById(long taskId) throws TaskNotFoundException {
        if (!dataStorage.getTaskById(taskId).isPresent()) throw new TaskNotFoundException();
        return dataStorage.getTaskById(taskId).get();
    }

    @Override
    public User getUserById(long userId) throws UserNotFoundException {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.getUser(userId).get();
    }

    @Override
    public Task updateTask(long taskId, TaskData taskData) throws Exception {
        if (!dataStorage.getTaskById(taskId).isPresent()) throw new TaskNotFoundException();
        try(final AutoCloseableReentrantLock lock = locksProvider.provideTaskLock(taskId).open()) {
            if (sleep) {
                log.info("Спим 20 секунд");
                sleep = false;
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                log.info("Не спим");
                sleep = true;
            }
            return dataStorage.updateTask(taskId, taskData);
        }
    }


    @Override
    public void deleteTask(long taskId) throws Exception {
        if (!dataStorage.getTaskById(taskId).isPresent()) throw new TaskNotFoundException();
        try(final AutoCloseableReentrantLock lock = locksProvider.provideTaskLock(taskId).open()) {
            log.info("Task to delete: " + dataStorage.getTaskById(taskId));
            dataStorage.deleteTask(taskId);
        }
    }

    @Override
    public void deleteUser(long userId) throws Exception {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        try (final AutoCloseableReentrantLock lock = locksProvider.provideUserLock(userId)){
            dataStorage.deleteUser(userId);
        }
    }

    @Override
    public List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.getAllTasks(userId);
    }

    @Override
    public List<Task> getAllActiveTaskOfUser(long userId) throws UserNotFoundException {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.getAllActiveTask(userId);
    }

}
