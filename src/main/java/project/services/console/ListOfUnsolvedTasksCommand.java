package project.services.console;

import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public class ListOfUnsolvedTasksCommand implements Command{
    private Model model;
    private User user;
    private String title;

    public ListOfUnsolvedTasksCommand(Model model, User user, String title) {
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
        if (model.getAllActiveTaskOfUser(user.getId()).isEmpty()) {
            System.out.println("Список активных задач пуст");
            return;
        }
        System.out.println("Список активных задач");
        model.getAllActiveTaskOfUser(user.getId()).forEach(System.out::println);
    }
}
