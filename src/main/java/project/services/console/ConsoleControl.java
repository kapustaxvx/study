package project.services.console;



import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ilia Moskalenko
 */
public class ConsoleControl {
    private Map<String, Command> commandMap;
    private Map<String, CommandForLogin> commandForLoginMap;
    private User user;

    public ConsoleControl()  {
        commandForLoginMap = new HashMap<>();
    }

    public ConsoleControl(User user) {
        this.user = user;
        commandMap = new HashMap<>();
    }


    public void setCommand(String commandName, Command command){
        commandMap.put(commandName, command);
    }
    public void setCommand(String commandName, CommandForLogin commandForLogin) {
        commandForLoginMap.put(commandName, commandForLogin);
    }

    public void commandWasChosen(BufferedReader bufferedReader) throws Exception {
        String commandName = bufferedReader.readLine().toLowerCase().trim();
        if (commandMap.containsKey(commandName))
        commandMap.get(commandName).execute(bufferedReader, user);
        else if (commandName.equals("help")) System.out.println(this.toString());
        else System.err.println("Такой команды не существует");
    }

    public User commandWasChosenForUser(BufferedReader bufferedReader) throws IOException, UserNotFoundException, UserExistsException {
        String commandName = bufferedReader.readLine().toLowerCase().trim();
        if (commandForLoginMap.containsKey(commandName))
           return commandForLoginMap.get(commandName).execute(bufferedReader);
        else if (commandName.equals("help")) {
            System.out.println(this.toStringForUserCommands());
            return null;
        }
        else {
            System.err.println("Такой команды не существует");
            return null;
        }
    }

    public String toStringForUserCommands(){
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("\n------ Remote Control -------\n");
        for (Map.Entry<String, CommandForLogin> pair :  commandForLoginMap.entrySet()){
            stringBuffer.append("[Command: ").append(pair.getKey()).append("] ")
                    .append(pair.getValue().getTitle()).append("\n");
        }
        return stringBuffer.toString();
    }

    public String toString(){
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("\n------ Remote Control -------\n");
        for (Map.Entry<String, Command> pair : commandMap.entrySet()){
            stringBuffer.append("[Command: ").append(pair.getKey()).append("] ")
                    .append(pair.getValue().getTitle()).append("\n");
        }
        return stringBuffer.toString();
    }
}
