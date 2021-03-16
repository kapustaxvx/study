package project.services.console;

import project.exceptions.UserExistsException;
import project.models.Model;
import project.models.User;
import project.models.UserData;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public class RegistrationUserCommand  implements CommandForLogin{
    private Model model;
    private String title;

    public RegistrationUserCommand(Model model, String title) {
        this.model = model;
        this.title = title;

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public User execute(BufferedReader bufferedReader) throws IOException, UserExistsException {
        User user;
        while (true){
        try {
            String name;
            String secondName;
            do {
                System.out.println("Введите имя: ");
                name = bufferedReader.readLine();
            } while (name.trim().isEmpty());
            do {
                System.out.println("Введите фамилию: ");
                secondName = bufferedReader.readLine();
            } while (secondName.trim().isEmpty());
            user = model.createUser(new UserData(name, secondName));
            System.out.println(user);
            break;
        } catch (UserExistsException e) {
            System.err.println("Пользователь с таким именем уже существует.");
        }
        }
        return user;
    }

}
