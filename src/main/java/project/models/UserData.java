package project.models;

import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class UserData {

    private  String name;
    private  String secondName;

    public UserData(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    UserData() {
    }

    public UserData update(String name, String secondName) {
        return new UserData(name, secondName);
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData)) return false;
        UserData userData = (UserData) o;
        return Objects.equals(name, userData.name) &&
                Objects.equals(secondName, userData.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName);
    }

    @Override
    public String toString() {
        return "name: " + name +
                ", secondName: " + secondName;
    }
}
