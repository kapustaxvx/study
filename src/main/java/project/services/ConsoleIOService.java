package project.services;

import org.springframework.stereotype.Component;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.User;
import project.services.console.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Component
public class ConsoleIOService implements IOService {
//    abstract void output(String s);
//    abstract String readLine();

    private Model model;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            User user = null;
            ConsoleControl consoleControlForRegistrationOrLogin = new ConsoleControl();
            RegistrationUserCommand registrationUserCommand = new RegistrationUserCommand(model,
                    "Регистрация пользователя");
            LoginUserCommand loginUserCommand = new LoginUserCommand(model, "Залогиниться");
            ExitCommand exitCommand = new ExitCommand(model, "Выход");
            consoleControlForRegistrationOrLogin.setCommand("reg", registrationUserCommand);
            consoleControlForRegistrationOrLogin.setCommand("login", loginUserCommand);
            consoleControlForRegistrationOrLogin.setCommand("exit", exitCommand);



            System.out.println(consoleControlForRegistrationOrLogin.toStringForUserCommands());
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
                while (user == null && exitCommand.isConnect()) {
                    System.out.println("Введите \"help\" для списка команд");
                    user = consoleControlForRegistrationOrLogin.commandWasChosenForUser(bufferedReader);
                }
                if (!exitCommand.isConnect()) return;

                ConsoleControl consoleControl = new ConsoleControl(user);
                CreateTaskCommand createTaskCommand = new CreateTaskCommand(model, user, "Создать задачу");
                UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(model, user, "Обновить задачу");
                ListOfUnsolvedTasksCommand listOfUnsolvedTasksCommand = new ListOfUnsolvedTasksCommand(model, user,
                        "Выдать список нерешенных задач");
                ListOfUserTasksCommand listOfUserTasksCommand = new ListOfUserTasksCommand(model, user,
                        "Выдать список всех задач");
                RemoveTaskCommand removeTaskCommand = new RemoveTaskCommand(model, user, "Удалить задачу");
                RemoveUserCommand removeUserCommand = new RemoveUserCommand(model, user, "Удалить ползователя");
                ExitSessionCommand exitSessionCommand = new ExitSessionCommand(model, user, "Выход");

                consoleControl.setCommand("create", createTaskCommand);
                consoleControl.setCommand("update", updateTaskCommand);
                consoleControl.setCommand("active", listOfUnsolvedTasksCommand);
                consoleControl.setCommand("all", listOfUserTasksCommand);
                consoleControl.setCommand("remove", removeTaskCommand);
                consoleControl.setCommand("delete", removeUserCommand);
                consoleControl.setCommand("exit", exitSessionCommand);


                System.out.println(consoleControl.toString());
                while (exitSessionCommand.isConnected()) {
                    System.out.println("Введите \"help\" для списка команд");
                    consoleControl.commandWasChosen(bufferedReader);
                }
            } catch (IOException | UserNotFoundException | UserExistsException
                    e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public ConsoleIOService(Model model) {
        this.model = model;
    }

    @Override
    public void start() {
        new Thread(runnable).start();
    }

    @Override
    public void stop() {

    }
}
