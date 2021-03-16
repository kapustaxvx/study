package project.services.console;

import project.models.Model;
import project.models.User;

import java.io.BufferedReader;


/**
 * @author Ilia Moskalenko
 */
public class RemoveUserCommand implements Command{
    private Model model;
    private User user;
    private String title;

    public RemoveUserCommand(Model model, User user, String title) {
        this.model = model;
        this.user = user;
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void execute(BufferedReader bufferedReader, User user) throws Exception {
        System.out.println("Вы цверены что хотите удалить потльзователя "+ user.toString()+"\n" +
                "Если уверенны, введите: y");
        String s = bufferedReader.readLine();
        if (s.toLowerCase().trim().equals("y")){
        model.deleteUser(user.getId());
        System.out.println("Пользователь " + user + " удален.");
        } else System.err.println("Пользователь не удален");
    }
}
