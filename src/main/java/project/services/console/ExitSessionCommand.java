package project.services.console;

import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public class ExitSessionCommand implements Command{
    private Model model;
    private User user;
    private String title;
    private boolean isConnect;

    public ExitSessionCommand(Model model, User user, String title) {
        this.model = model;
        this.user = user;
        this.title = title;
        isConnect = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setConnected(boolean connected) {
        isConnect = connected;
    }

    public boolean isConnected() {
        return isConnect;
    }

    @Override
    public void execute(BufferedReader bufferedReader, User user) throws IOException, UserNotFoundException {
        setConnected(false);
    }
}
