package hyperquiz.util;

import hyperquiz.dao.impl.LongKeyGenerator;
import hyperquiz.model.Quiz;
import hyperquiz.model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StreamUtil {

    private static ObjectOutputStream OUT;
    private static LongKeyGenerator keyGen=new LongKeyGenerator();

    static {
        try {
            if(Files.exists(Path.of("Entities.data"))){
                List<User> users = MenuUtil.readUsers();
                List<Quiz> quizzes=MenuUtil.readQuizzes();
                OUT = new ObjectOutputStream(new FileOutputStream("Entities.data"));
                for(User user:users){
                    OUT.writeObject(user);
                }
                for(Quiz q:quizzes){
                    OUT.writeObject(q);
                }
                OUT.writeObject(keyGen);
            }else{
                OUT = new ObjectOutputStream(new FileOutputStream("Entities.data"));
            }


        } catch (IOException e) {

        }
    }
    public static void createUser(User user) {
        try {
            OUT.writeObject(user);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void createQuiz(Quiz quiz) {
        try {
            OUT.writeObject(quiz);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
