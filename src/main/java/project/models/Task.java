package project.models;

import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class Task extends TaskData {
    private final long id;
    private long userId;


    public Task(long userId, long id, String title, String fullTaskText, boolean solved) {
        super(title, fullTaskText, solved);
        this.userId = userId;
        this.id = id;
    }

    public Task updateId(long id) {
        return new Task(userId, id, getTitle(), getFullTaskText(), isSolved());
    }


    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return id == task.id &&
                userId == task.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, userId);
    }

    @Override
    public String toString() {
        return "User id: " + userId + "\nTask id: " + id + " " +
                super.toString();
    }

}
