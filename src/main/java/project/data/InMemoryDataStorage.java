package project.data;

import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class InMemoryDataStorage implements DataStorage {
    private long countUser;
    private Map<Long, User> userList = new HashMap<>();
    private long countTask;
    private Map<Long, Task> taskList = new HashMap<>();
    private Map<Long, Set<Long>> usersTasksMap = new HashMap<>();

    @Override
    public Task addTask(long userId, TaskData taskData) {
        countTask++;
        taskList.put(countTask, new Task(userId, countTask, taskData.getTitle(), taskData.getFullTaskText(), taskData.isSolved()));
        Set<Long> userTasks = usersTasksMap.computeIfAbsent(userId, k -> new HashSet<>());
//        Set<Long> userTasks = usersTasksMap.get(userId);
//        if (userTasks == null) {
//            userTasks = new HashSet<>();
//            usersTasksMap.put(userId, userTasks);
//        }

        userTasks.add(countTask);

        return taskList.get(countTask);

    }

    @Override
    public Task updateTask(long taskId, TaskData taskData) {
        Task oldTask = taskList.get(taskId);
        Task newTask = new Task(oldTask.getUserId(),
                oldTask.getId(),
                taskData.getTitle(),
                taskData.getFullTaskText(),
                taskData.isSolved());
        taskList.put(taskId, newTask);

        return taskList.get(taskId);
    }

    @Override
    public Optional<Task> getTaskById(long taskId) {
        if (taskList.containsKey(taskId)) return Optional.of(taskList.get(taskId));
        return Optional.empty();
    }

    @Override
    public void deleteTask(long taskId) {
        Task taskToRemove = taskList.remove(taskId);
        if (taskToRemove != null) {
            Set<Long> tasksOfUser = usersTasksMap.get(taskToRemove.getUserId());
            if (tasksOfUser != null) tasksOfUser.remove(taskId);
        }
    }

    @Override
    public List<Task> getAllActiveTask(long userId) {
//        List<Task> allActiveTasksOfUser = new ArrayList<>();
//        for (Long ids : usersTasksMap.get(userId)){
//            if (!taskList.get(ids).isSolved()){
//                allActiveTasksOfUser.add(taskList.get(ids));
//            }
//        }

        return getAllTasks(userId, true);
    }

    private List<Task> getAllTasks(long userId, boolean onlyActive) {
        Set<Long> userTaskIds = usersTasksMap.get(userId);
        if (userTaskIds == null) return new ArrayList<>();
        return userTaskIds.stream()
                .flatMap(taskId -> getTaskById(taskId).map(t -> Stream.of(t)).orElseGet(() -> Stream.empty()))
                .filter(task -> !task.isSolved() || (!onlyActive))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasks(long userId) {
        return getAllTasks(userId, false);
    }

    @Override
    public User addUser(UserData userData) {
        countUser++;
        userList.put(countUser, new User(countUser, userData.getName(), userData.getSecondName()));
        return userList.get(countUser);
    }

    @Override
    public User updateUser(long userId, UserData userData) {
        userList.put(userId, new User(userId,
                userData.getName(),
                userData.getSecondName()));
        return userList.get(userId);
    }

    @Override
    public void deleteUser(long userId) {
        userList.remove(userId);
        Set<Long> usersTasks = usersTasksMap.remove(userId);
        if (usersTasks != null) usersTasks.forEach(taskId -> taskList.remove(taskId));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userList.values());
    }


    @Override
    public Optional<User> getUser(long userId) {
        return Optional.ofNullable(userList.get(userId));
    }
}
