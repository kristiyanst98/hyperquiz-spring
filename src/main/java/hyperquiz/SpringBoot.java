package hyperquiz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBoot {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBoot.class, args);
        ctx.registerShutdownHook();
    }
}