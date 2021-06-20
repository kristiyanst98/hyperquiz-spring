package hyperquiz.model;
import com.fasterxml.jackson.annotation.JsonView;
import hyperquiz.view.View;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="quizresults")
@Entity
public class QuizResult extends AbstractEntity<Long,QuizResult>{

    @ManyToOne
    @NonNull
    @JsonView(View.QuizResultView.External.class)
    private User player;

    @ManyToOne
    @NonNull
    @JsonView(View.QuizResultView.External.class)
    private Quiz quiz;

    @Transient
    private int score;

    public QuizResult() {
    }

    public QuizResult(User player, Quiz quiz, int score) {
        this.player = player;
        this.quiz = quiz;
        this.score = score;
    }

    public QuizResult(@NonNull User player, @NonNull Quiz quiz) {
        this.player = player;
        this.quiz = quiz;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuizResult{");
        sb.append("player=").append(player);
        sb.append(", quiz=").append(quiz);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
