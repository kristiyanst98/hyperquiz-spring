package hyperquiz.util;

import hyperquiz.model.Answer;
import hyperquiz.model.Question;

import java.util.Scanner;

public class AnswerUtil {

    public static Answer createAnswer(Question question){
        Scanner scanner = new Scanner(System.in);
        Answer answer = new Answer();
        answer.setQuestion(question);
        do {
            System.out.println("Enter answer text:");
            String text = scanner.nextLine();
            if(ValidationUtil.validateString(text,2,100)) {
                answer.setText(text);
                break;
            }else{
                System.out.println("Answer text must be between 2 and 100 characters");
            }
        }while(true);
        do {
            System.out.println("Enter score:");
            int score = scanner.nextInt();
            if(ValidationUtil.validateNumber(score)) {
                answer.setScore(score);
            break;
            }else{
                System.out.println("Enter a positive number");
            }
        }while(true);
        return answer;

    }
}
