package project.services.console;

import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public interface CommandForLogin {

    User execute(BufferedReader bufferedReader) throws IOException, UserExistsException, UserNotFoundException;
    String getTitle();
}
