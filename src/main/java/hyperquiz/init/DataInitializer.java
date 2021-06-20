package hyperquiz.init;

import hyperquiz.dao.QuizRepository;
import hyperquiz.dao.QuizResultRepository;
import hyperquiz.dao.UserRepository;
import hyperquiz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository ur;

    @Autowired
    QuizRepository qr;

    @Autowired
    QuizResultRepository qrr;

    User[] users = {
            new User("user1", "user1@abv.bg", "password1", Gender.MALE, Role.PLAYER, "what is this description I dont know", "01x2", true),
            new User("admin1", "user2@abv.bg", "password2", Gender.MALE, Role.ADMIN, "what is this description I dont know", "02x3", true),
            new User("user3", "user3@abv.bg", "password3", Gender.MALE, Role.PLAYER, "what is this description I dont know", "03x4", true)

    };

    Quiz[] quizzes = {
            new Quiz("An Easy Quiz",users[0],"This is a really easy quiz",5,"#easy"),
            new Quiz("An Intermediate Quiz",users[2],"This is an intermediate quiz",10,"#intermediate"),
            new Quiz("A Hard Quiz",users[1],"This is a really hard quiz",15,"#hard")
    };

    QuizResult[] quizResults = {
            new QuizResult(),
            new QuizResult(),
            new QuizResult()
    };

    @Override
    public void run(String... args){
        if (ur.count() == 0) {
            ur.createBatch(Arrays.asList(users));
        }
        quizzes[0].setAuthor(ur.findById(3L).get());
        quizzes[1].setAuthor(ur.findById(3L).get());
        quizzes[2].setAuthor(ur.findById(2L).get());
        if(qr.count()==0){
            qr.createBatch(Arrays.asList(quizzes));
        }
        quizResults[0].setPlayer(ur.findById(1L).get());
        quizResults[0].setQuiz(qr.findById(1L).get());

        quizResults[1].setPlayer(ur.findById(2L).get());
        quizResults[1].setQuiz(qr.findById(2L).get());

        quizResults[2].setPlayer(ur.findById(3L).get());
        quizResults[2].setQuiz(qr.findById(3L).get());

        if(qrr.count()==0){
            qrr.createBatch(Arrays.asList(quizResults));
        }

    }
}
