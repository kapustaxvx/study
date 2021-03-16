package project.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.data.DataStorage;
import project.data.InMemoryDataStorage;
import project.data.JDBCDataStorage;
import project.data.JpaDataStorage;
import project.models.entity.TaskRepo;
import project.models.entity.UserRepo;

@Configuration
@EnableConfigurationProperties(DataStorageProperties.class)
public class DataStorageConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DataStorageConfiguration.class);

    @Bean
    public DataStorage createDataStorage(DataStorageProperties dataStorageProperties,
                                         TaskRepo taskRepo, UserRepo userRepo){
        switch (dataStorageProperties.getActive()) {
            case IN_MEMORY:
                log.info("Creating InMemoryDataStorage");
                return new InMemoryDataStorage();
            case JDBC:
                log.info("Creating JDBCDataStorage");
                return new JDBCDataStorage();
            default:
                log.info("Creating JpaDataStorage");
                return new JpaDataStorage(userRepo, taskRepo);
        }
    }
}
