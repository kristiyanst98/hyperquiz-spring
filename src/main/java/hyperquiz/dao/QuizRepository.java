package hyperquiz.dao;

import hyperquiz.model.Quiz;

import java.util.Set;

public interface QuizRepository extends Repository <Long, Quiz> {
    Set<Quiz> findByExpectedDuration(int expectedDuration);
    Set<Quiz>findByTitle(String title);
    Set<Quiz> findByDescription(String description);
    Set<Quiz> findByTags(String tags);
    Set<Quiz> findByTTD(String criterias);

}
