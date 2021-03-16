package project.services.console;

import project.exceptions.TaskNotFoundException;
import project.models.Model;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public class UpdateTaskCommand implements Command {
    private Model model;
    private User user;
    private String title;

    public UpdateTaskCommand(Model model, User user, String title) {
        this.model = model;
        this.user = user;
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void execute(BufferedReader bufferedReader, User user) throws IOException {

        while (true) {
            try {
                System.out.println("Введите id задачи которую хотите обновить: ");
                long id = Long.parseLong(bufferedReader.readLine().trim());
                model.updateTask(id, model.getTaskById(id).solvedTask(true));
                System.out.println("Задача id: " + id + " обновлена.");
                break;
            } catch (NumberFormatException e) {
                System.err.println("Введите целое число");
            } catch (TaskNotFoundException e) {
                System.err.println("Такой задачи не обнаружено");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
