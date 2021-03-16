package project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("data-storage")
public class DataStorageProperties {

    private DataStorageType active;

    public DataStorageType getActive() {
        return active;
    }

    public void setActive(DataStorageType active) {
        this.active = active;
    }

    public enum DataStorageType{
        JDBC,
        IN_MEMORY,
        JPA
    }
}
