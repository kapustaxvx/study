package project.services.console;

import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public class ExitCommand implements CommandForLogin{
    private Model model;
    private boolean isConnect;
    private String title;

    public ExitCommand(Model model, String title) {
        this.model = model;
        this.title = title;
        isConnect = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    public boolean isConnect() {
        return isConnect;
    }

    @Override
    public User execute(BufferedReader bufferedReader) throws IOException, UserExistsException, UserNotFoundException {
        setConnect(false);
        return null;
    }
}
