package project.models.entity;

import project.models.UserData;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "User")
@Table(
        name = "USERS",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_name_unique",
                        columnNames = "NAME")
        }
)
public class UserEntity implements Serializable {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "USER_ID",
            updatable = false
    )
    private long userId;

    @Column(
            name = "NAME",
            nullable = false
    )
    private String name;
    @Column(
            name = "SECOND_NAME",
            nullable = false
    )
    private String secondName;

    public UserEntity() {
    }

    public UserEntity(UserData user) {
        this.name = user.getName();
        this.secondName = user.getSecondName();
    }

  /*  @OneToMany(fetch = FetchType.EAGER, mappedBy = "userEntity")
    private List<TaskEntity> taskList;*/

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

/*    public List<TaskEntity> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskEntity> taskList) {
        this.taskList = taskList;
    }*/

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
