package hyperquiz.services.impl;

import hyperquiz.dao.QuizRepository;
import hyperquiz.model.Quiz;
import hyperquiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository qr;


    @Override
    @Transactional(readOnly = true)
    public List<Quiz> getAllQuizzes() {
        return qr.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Quiz findQuizByID(Long id) {
        return qr.findById(id).get();
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return qr.create(quiz);
    }

    @Override
    public Set<Quiz> findQuizByDuration(int duration) {
        return qr.findByExpectedDuration(duration);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return qr.update(quiz);
    }

    @Override
    public Quiz deleteQuizByID(Long id) {
        return qr.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Quiz> findQuizByTitle(String title) {
        return qr.findByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Quiz> findQuizByDescription(String description) {
        return qr.findByDescription(description);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Quiz> findQuizByTags(String tags) {
        return qr.findByTags(tags);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Quiz> findQuizzesByWords(String words) {
        return qr.findByTTD(words);
    }

    @Override
    @Transactional(readOnly = true)
    public long countQuizzes() {
        return qr.count();
    }

    @Override
    public List<Quiz> createQuizBatch(Collection<Quiz> collection) {
        return qr.createBatch(collection);
    }

}
