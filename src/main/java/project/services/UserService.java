package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.User;
import project.models.UserData;

@Component
public class UserService {

    @Autowired
    private Model model;

    public User createUser(UserData userData) throws UserExistsException {
        return model.createUser(userData);
    }

    public User getUserById(long userId) throws UserNotFoundException {
        return model.getUserById(userId);
    }

    public void deleteUser(long userId) throws Exception {
        model.deleteUser(userId);
    }


}
