package project.services.console;


import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.Task;
import project.models.TaskData;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public class CreateTaskCommand implements Command {
    private Model model;
    private User user;
    private String title;

    public CreateTaskCommand(Model model, User user, String title) {
        this.model = model;
        this.user = user;
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void execute(BufferedReader bufferedReader, User user) throws IOException, UserNotFoundException {
        String title;
        String taskText;
        do {
            System.out.println("Введите название задачи: ");
            title = bufferedReader.readLine().trim();
        } while (title.isEmpty());
        do {
            System.out.println("Введите задачу целиком: ");
            taskText = bufferedReader.readLine().trim();
        } while (taskText.isEmpty());
        Task task = model.createTask(user.getId(), new TaskData(title, taskText, false));
        System.out.println("Создана задача: ");
        System.out.println(task.toString());
    }
}
