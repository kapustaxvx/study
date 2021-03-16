package project.services.console;


import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public class LoginUserCommand implements CommandForLogin {
    private Model model;
    private String title;

    public LoginUserCommand(Model model, String title) {
        this.model = model;
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public User execute(BufferedReader bufferedReader) throws  UserNotFoundException {
        User user;
        while (true) {
            try {
                System.out.println("Введите id для того чтобы залогиниться: ");
                long id = Long.parseLong(bufferedReader.readLine().trim());
                user = model.getUserById(id);
                System.out.println(user);
                break;
            } catch (NumberFormatException | IOException e) {
                System.err.println("Введите целое число");
            } catch (UserNotFoundException e){
                System.err.println("Такого пользователя не сущетсвует.");
            }
        }
        return user;
    }

}
