package project.models;

import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class User extends UserData {
    private final long id;

    public User(long id, String name, String secondName) {
        super(name, secondName);
        this.id = id;
    }

    public User updateId(long id) {
        return new User(id, getName(), getSecondName());
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "id: " + id + " " +
                super.toString();
    }
}
