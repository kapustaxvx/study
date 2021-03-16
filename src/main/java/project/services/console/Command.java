package project.services.console;

import project.models.User;

import java.io.BufferedReader;

/**
 * @author Ilia Moskalenko
 */
public interface Command {
     void execute(BufferedReader bufferedReader, User user) throws Exception;
     String getTitle();
}
