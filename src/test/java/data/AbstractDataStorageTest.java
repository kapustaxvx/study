package data;


import org.junit.Before;
import org.junit.Test;
import project.data.DataStorage;
import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


public abstract class AbstractDataStorageTest {
    public DataStorage storage;
    public int taskId = 12;
    public int userId = 22;
    public abstract DataStorage createDataStorage();


    @Before
    public void before(){
        storage = createDataStorage();
    }

    @Test
    public void addUser() {
        int id = 32;
        User user = storage.addUser(new UserData("a", "b"));
        assertEquals(new User(id,"a", "b"), user);
    }

  /*  @Test
    public void addUserWithNullName(){
        int id = 32;
        User user = storage.addUser(new UserData("", "b"));
    }
*/
  /*  @Test(expected = UserExistsException.class)
    public void addExistUser(){
        int id = 31;
        User user = storage.addUser(new UserData("a", "b"));
    }*/

    @Test
    public void getUser() {
        User user = storage.getUser(userId).get();
        assertEquals(new User(userId,"a", "b"), user);
    }

    @Test
    public void deleteUser(){
        int id = 20;
        storage.deleteUser(id);
        assertEquals(Optional.empty(), storage.getUser(id));
    }
/*
    @Test(expected = UserNotFoundException.class)
    public void deleteNotExistUser(){
        storage.deleteUser(1);
    }*/

    @Test
    public void addTask(){
        Task task = storage.addTask(userId,new TaskData("t1","task1",false));
        assertEquals(new Task(userId,taskId,"t1","task1",false), storage.getTaskById(task.getId()).get());
    }

    @Test
    public void getTask(){
        Task task = storage.getTaskById(taskId).get();
        assertEquals(new Task(userId,taskId,"t1","task1",false), task);
    }

    @Test
    public void updateTask(){
        TaskData task = storage.getTaskById(taskId).get().solvedTask(true);
        Task task1 = storage.updateTask(taskId, task);
        assertEquals(new Task(userId,taskId,"t1","task1",true), task1);
    }

    @Test
    public void deleteTask(){
        storage.deleteTask(taskId);
        assertEquals(Optional.empty(), storage.getTaskById(taskId));
    }


}