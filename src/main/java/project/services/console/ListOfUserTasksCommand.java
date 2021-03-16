package project.services.console;

import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public class ListOfUserTasksCommand implements Command{
    private Model model;
    private User user;
    private String title;

    public ListOfUserTasksCommand(Model model, User user, String title) {
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
        if (model.getAllTasksOfUser(user.getId()).isEmpty()) {
            System.out.println("Список задач пуст");
            return;
        }
        System.out.println("Список всех задач");
        model.getAllTasksOfUser(user.getId()).forEach(System.out::println);
    }
}
