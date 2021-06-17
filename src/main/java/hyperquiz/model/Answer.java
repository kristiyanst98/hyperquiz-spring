package hyperquiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.net.URL;

@Entity
@Table(name="answers")
public class Answer extends AbstractEntity<Long,Answer> implements Serializable {

    @ManyToOne
    private Question question;

    @Column(nullable = false)
    @Size(min = 2, max = 150, message = "Answer must be between 2 and 150 characters")
    private String text;

    @Column
    private URL picture;

    @Column(nullable = false)
    private int score;

    public Answer() {
    }

    public Answer(Question question, String text, int score) {
        this.question = question;
        this.text = text;
        this.score = score;
    }

    public Answer(Question question, String text, URL picture, int score) {
        this.question = question;
        this.text = text;
        this.picture = picture;
        this.score = score;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Answer{");
        sb.append("question=").append(question);
        sb.append(", text='").append(text).append('\'');
        sb.append(", picture=").append(picture);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
