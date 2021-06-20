package hyperquiz.model;

import com.fasterxml.jackson.annotation.JsonView;
import hyperquiz.view.View;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Table(name="quizzes")
@Entity
public class Quiz extends AbstractEntity<Long,Quiz> {

    @JsonView(View.QuizView.External.class)
    @Column(nullable = false,unique = true)
    @Size(min=2, max=80,message = "Title must be between 2 and 80 characters")
    private String title;

    @JsonView(View.QuizView.External.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @JsonView(View.QuizView.External.class)
    @Column
    @Size(min=20, max=150,message = "Description must be between 20 and 150 characters")
    private String description;

    @JsonView(View.QuizView.Internal.class)
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions=new ArrayList<>();

    @JsonView(View.QuizView.External.class)
    @Column(nullable = false)
    private int expectedDuration;

    @JsonView(View.QuizView.External.class)
    @Column
    private URL picture;

    @JsonView(View.QuizView.External.class)
    @Column
    private String tags;

    public Quiz() {

    }

    public Quiz(Long id) {
        super(id);
    }

    public Quiz(String title, User author, String description, int expectedDuration, String tags) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.tags = tags;
        this.questions=new ArrayList<>();
    }

    public Quiz(String title, User author, String description, int expectedDuration, URL picture, String tags) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.questions = new ArrayList<>();
        this.expectedDuration = expectedDuration;
        this.picture = picture;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(int expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.title);
        return sb.toString();
    }
}
