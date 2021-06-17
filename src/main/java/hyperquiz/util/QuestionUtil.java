package hyperquiz.util;

import hyperquiz.model.Question;
import hyperquiz.model.Quiz;

import java.util.Scanner;

public class QuestionUtil {

    public static Question createQuestion(Quiz quiz){
        Scanner scanner = new Scanner(System.in);
        System.out.println("=NOW CREATING QUESTION FOR QUIZ: "+quiz.getTitle());
        Question question = new Question();
        question.setQuiz(quiz);
        do {
            System.out.println("Enter question:");
            String text = scanner.nextLine();
            if(ValidationUtil.validateString(text,2,150)) {
                question.setText(text);
            break;
            }else{
                System.out.println("Text must be between 2 and 150 characters");
            }
        }while(true);
        question.getAnswers().add(AnswerUtil.createAnswer(question));

        return question;
    }
}
