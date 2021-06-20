package hyperquiz.services;

import hyperquiz.model.Quiz;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface QuizService {

    public List<Quiz> getAllQuizzes();

    public Quiz findQuizByID(Long id);

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Quiz deleteQuizByID(Long id);

    public long countQuizzes();

    public List<Quiz> createQuizBatch(Collection<Quiz> collection);

    public Set<Quiz> findQuizByTitle(String title);

    public Set<Quiz> findQuizByDescription(String description);

    public Set<Quiz> findQuizByTags(String tags);

    public Set<Quiz> findQuizByDuration(int duration);

    public Set<Quiz> findQuizzesByWords(String words);
}