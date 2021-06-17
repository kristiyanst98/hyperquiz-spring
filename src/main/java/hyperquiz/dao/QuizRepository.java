package hyperquiz.dao;

import hyperquiz.model.Quiz;

import java.util.Optional;
import java.util.Set;

public interface QuizRepository extends Repository <Long, Quiz> {
    Optional<Quiz> findByExpectedDuration(int expectedDuration);
    Optional<Quiz> findByTitle(String title);
    Optional<Quiz> findByDescription(String description);
    Optional<Quiz> findByTags(String tags);
    Set<Quiz> findByTTD(String criterias);

}
