package project.models;

import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class TaskData {
    private  String title;
    private  String fullTaskText;
    private  boolean solved;



    public TaskData(String title, String fullTaskText, boolean solved) {
        this.title = title;
        this.fullTaskText = fullTaskText;
        this.solved = solved;
    }

    TaskData() {
    }

    public TaskData solvedTask(boolean solved) {
        return new TaskData(title, fullTaskText, solved);
    }

    public String getTitle() {
        return title;
    }

    public String getFullTaskText() {
        return fullTaskText;
    }

    public boolean isSolved() {
        return solved;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskData)) return false;
        TaskData taskData = (TaskData) o;
        return solved == taskData.solved &&
                Objects.equals(title, taskData.title) &&
                Objects.equals(fullTaskText, taskData.fullTaskText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, fullTaskText, solved);
    }

    @Override
    public String toString() {
        return "title: " + title +
                ", fullTaskText: " + fullTaskText + '\n' +
                "solved: " + solved;
    }
}
