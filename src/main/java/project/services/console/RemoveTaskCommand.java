package project.services.console;

import project.exceptions.TaskNotFoundException;
import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public class RemoveTaskCommand implements Command{
    private Model model;
    private User user;
    private String title;

    public RemoveTaskCommand(Model model, User user, String title) {
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
        while (true) {
            try {
                System.out.println("Введите id задачи которую хотите удалить: ");
                long id = Long.parseLong(bufferedReader.readLine().trim());
                model.deleteTask(id);
                System.out.println("Задача id: " + id + " удалена.");
                break;
            } catch (NumberFormatException e) {
                System.err.println("Введите целое число");
            } catch (TaskNotFoundException e ){
                System.err.println("Задачи с таким id не существует");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
