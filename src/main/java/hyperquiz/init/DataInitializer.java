package hyperquiz.init;

import hyperquiz.dao.UserRepository;
import hyperquiz.model.Gender;
import hyperquiz.model.Role;
import hyperquiz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository ur;

    User[] users = {
            new User("user1", "user1@abv.bg", "password1", Gender.MALE, Role.PLAYER, "what is this description I dont know", "01x2", true),
            new User("admin1", "user2@abv.bg", "password2", Gender.MALE, Role.ADMIN, "what is this description I dont know", "02x3", true),
            new User("user3", "user3@abv.bg", "password3", Gender.MALE, Role.PLAYER, "what is this description I dont know", "03x4", true)

    };

    @Override
    public void run(String... args){
        if (ur.count() == 0) {
            ur.createBatch(Arrays.asList(users));
        }
    }
}
