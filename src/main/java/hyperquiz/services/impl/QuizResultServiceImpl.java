package hyperquiz.services.impl;

import hyperquiz.dao.QuizResultRepository;
import hyperquiz.model.QuizResult;
import hyperquiz.services.QuizResultService;
import hyperquiz.services.QuizService;
import hyperquiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class QuizResultServiceImpl implements QuizResultService {

    @Autowired
    QuizResultRepository qrr;

    @Autowired
    UserService us;

    @Autowired
    QuizService qs;


    @Override
    @Transactional(readOnly = true)
    public List<QuizResult> getAllQuizResults() {
        return qrr.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public QuizResult findQuizResultByID(Long id) {
        return qrr.findById(id).get();
    }

    @Override
    public QuizResult addQuizResult(QuizResult quizResult) {
        return qrr.create(quizResult);
    }

    @Override
    public QuizResult updateQuizResult(QuizResult quizResult) {
        return qrr.update(quizResult);
    }

    @Override
    public QuizResult deleteQuizResultByID(Long id) {
        return qrr.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countQuizResults() {
        return qrr.count();
    }

    @Override
    public List<QuizResult> createQuizResultBatch(Collection<QuizResult> collection) {
        return qrr.createBatch(collection);
    }
}
