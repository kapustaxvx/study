package project;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.services.IOService;


/*@Configuration
@ComponentScan(basePackages = "project")
@EnableJpaRepositories(basePackages = "project/models/entity")
@EntityScan("project/models/entity")*/
@SpringBootApplication
public class SpringApp {
    private final static Logger log = LoggerFactory.getLogger(SpringApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class).getBean(IOService.class).start();



        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApp.class);
        context.getBean(IOService.class).start();*/

    }
}
