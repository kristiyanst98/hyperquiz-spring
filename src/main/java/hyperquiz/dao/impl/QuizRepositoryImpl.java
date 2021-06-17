package hyperquiz.dao.impl;

import hyperquiz.dao.KeyGenerator;
import hyperquiz.dao.QuizRepository;
import hyperquiz.model.Quiz;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class QuizRepositoryImpl extends RepositoryMemoryImpl<Long, Quiz> implements QuizRepository {
    @Override
    public Optional<Quiz> findByExpectedDuration(int expectedDuration) {
        return this.findAll().stream().filter(quiz->quiz.getExpectedDuration()==expectedDuration).findFirst();
    }

    @Override
    public Optional<Quiz> findByTitle(String title) {
        return this.findAll().stream().filter(quiz->quiz.getTitle().equals(title)).findFirst();
    }

    @Override
    public Optional<Quiz> findByDescription(String description) {
        return this.findAll().stream().filter(quiz->quiz.getDescription().equals(description)).findFirst();
    }

    @Override
    public Optional<Quiz> findByTags(String tags) {
        return this.findAll().stream().filter(quiz->quiz.getTags().equals(tags)).findFirst();
    }

    @Override
    public Set<Quiz> findByTTD(String criteria) {
       return this.findAll().stream().filter(quiz->{
           if(quiz.getTitle().contains(criteria) || quiz.getDescription().contains(criteria) || quiz.getTags().contains(criteria))
               return true;
          else{
              return false;
           }
       }).collect(Collectors.toSet());
    }

    public QuizRepositoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
