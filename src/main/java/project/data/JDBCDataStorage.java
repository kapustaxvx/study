package project.data;

import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JDBCDataStorage implements DataStorage {
    private Connection connection;
    private final String dbPath;


    public JDBCDataStorage() {
        this("jdbc:h2:/home/ilia/home/IliaLessons/db/testDB");
    }

    public JDBCDataStorage(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection getConnection() throws SQLException {
        if (connection == null) {
            final String DB_USERNAME = "user";
            final String DB_PASSWORD = "password";
            connection = DriverManager.getConnection(dbPath, DB_USERNAME, DB_PASSWORD);
        }
        return connection;
    }


    @Override
    public Task addTask(long userId, TaskData task) {
        int taskId = 0;
        try (Statement statement = getConnection().createStatement()) {
            String insert = String.format("INSERT INTO tasks (fk_user_id, title, full_task_text, is_solved) " +
                    "VALUES (%d, '%s', '%s', '%b')", userId, task.getTitle(), task.getFullTaskText(), task.isSolved());
            statement.executeUpdate(insert);
            ResultSet resultSet = statement.executeQuery("SELECT task_id " +
                    "FROM tasks");
            resultSet.last();
            taskId = resultSet.getInt(1);
        } catch (SQLException e) {
            e.getMessage();
        }
        if (taskId == 0) return null;
        else return new Task(userId, taskId, task.getTitle(), task.getFullTaskText(), task.isSolved());
    }

    @Override
    public Task updateTask(long taskId, TaskData taskData) {
        Task task = null;
        String update = String.format("UPDATE tasks " +
                "SET title ='%s', full_task_text = '%s', is_solved = '%b' " +
                "WHERE task_id = %d", taskData.getTitle(), taskData.getFullTaskText(), taskData.isSolved(), taskId);
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(update);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks " +
                    "WHERE task_id = " + taskId);
            while (resultSet.next()) {
                task = new Task(resultSet.getInt(2),
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return task;
    }

    @Override
    public void deleteTask(long taskId) {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM tasks " +
                    "WHERE task_id = " + taskId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Task> getAllActiveTask(long userId) {
        List<Task> taskList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            Task task;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks " +
                    "WHERE is_solved = 'false' AND fk_user_id = " + userId);
            while (resultSet.next()) {
                task = new Task(resultSet.getInt(2),
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5));
                taskList.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (taskList.isEmpty()) return new ArrayList<>(0);
        else return taskList;
    }

    @Override
    public List<Task> getAllTasks(long userId) {
        List<Task> taskList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            Task task;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks " +
                    "WHERE fk_user_id = " + userId);
            while (resultSet.next()) {
                task = new Task(resultSet.getInt(2),
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5));
                taskList.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (taskList.isEmpty()) return new ArrayList<>(0);
        else return taskList;
    }

    @Override
    public User addUser(UserData user) {
        User newUser = null;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO users (name, second_name) VALUES('" + user.getName() + "','"
                    + user.getSecondName() + "')");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            resultSet.last();
            newUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newUser;
    }

    @Override
    public User updateUser(long userId, UserData userData) {
        User user = null;
        String update = String.format("UPDATE users " +
                "SET name ='%s', second_name = '%s' " +
                "WHERE task_id = %d", userData.getName(), userData.getSecondName(), userId);
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(update);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users " +
                    "WHERE user_id = " + userId);
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUser(long userId) {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM tasks " +
                    "WHERE fk_user_id = " + userId);
            statement.executeUpdate("DELETE FROM users " +
                    "WHERE user_id = " + userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            User user;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (userList.isEmpty()) return new ArrayList<>(0);
        else return userList;
    }

    @Override
    public Optional<Task> getTaskById(long taskId) {
        Task task = null;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks WHERE task_id = " + taskId);
            while (resultSet.next())
                task = new Task(resultSet.getInt(2),
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (task == null) return Optional.empty();
        else return Optional.of(task);
    }

    @Override
    public Optional<User> getUser(long userId) {
        User user = null;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE user_id = " + userId);
            while (resultSet.next())
                user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (user == null) return Optional.empty();
        else return Optional.of(user);
    }
}
