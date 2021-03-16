package project.models.entity;

import project.models.TaskData;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Task")
@Table(name = "TASKS")
public class TaskEntity implements Serializable {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    @Column(
            name = "TASK_ID",
            updatable = false
    )
    private long taskId;


    /*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID",
            nullable = false,
            referencedColumnName = "USER_ID")*/
    @Column(
            name = "USER_ID",
            nullable = false
    )
    private long userId;

    @Column(
            name = "TITLE",
            nullable = false
    )
    private String title;
    @Column(
            name = "FULL_TASK_TEXT",
            nullable = false
    )
    private String fullTaskText;
    @Column(
            name = "iS_SOLVED",
            nullable = false
    )
    private boolean isSolved;

    public TaskEntity() {
    }

    public TaskEntity(long userId, TaskData task) {
        this.userId = userId;
        this.title = task.getTitle();
        this.fullTaskText = task.getFullTaskText();
        this.isSolved = task.isSolved();
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullTaskText() {
        return fullTaskText;
    }

    public void setFullTaskText(String fullTaskText) {
        this.fullTaskText = fullTaskText;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }


    @Override
    public String toString() {
        return "TaskEntity{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", fullTaskText='" + fullTaskText + '\'' +
                ", isSolved=" + isSolved +
                '}';
    }
}
