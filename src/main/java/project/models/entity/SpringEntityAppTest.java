package project.models.entity;/*
package project.models.entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import project.models.TaskData;
import project.models.UserData;

@SpringBootApplication
public class SpringEntityAppTest {
    private static final Logger log = LoggerFactory.getLogger(SpringEntityAppTest.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringEntityAppTest.class);
    }

    @Bean
    public CommandLineRunner demo(TaskRepo taskRepo, UserRepo userRepo) {
        return args -> {
            log.info("START");
            UserEntity userEntity = new UserEntity(new UserData("name2", "second-name2"));
            TaskEntity taskEntity1 = new TaskEntity(userEntity, new TaskData("t1", "task1", false));
            TaskEntity taskEntity2 = new TaskEntity(userEntity, new TaskData("t2", "task2", false));

            userRepo.save(userEntity);
            log.info("USER after save: " + userEntity.toString());
            log.info("User id: " + userEntity.getUserId());

            taskRepo.save(taskEntity1);
            taskRepo.save(taskEntity2);
            log.info("USERS: ");
            userRepo.findAll().forEach(testEntity -> log.info(testEntity.toString()));
            log.info("TASKS: ");
            taskRepo.findAll().forEach(testEntity -> log.info(testEntity.toString()));

        };
    }
}
*/
