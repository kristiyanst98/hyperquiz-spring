package hyperquiz.services;

import hyperquiz.model.QuizResult;

import java.util.Collection;
import java.util.List;

public interface QuizResultService{

    public List<QuizResult> getAllQuizResults();

    public QuizResult findQuizResultByID(Long id);

    public QuizResult addQuizResult(QuizResult quizResult);

    public QuizResult updateQuizResult(QuizResult quizResult);

    public QuizResult deleteQuizResultByID(Long id);

    public long countQuizResults();

    public List<QuizResult> createQuizResultBatch(Collection<QuizResult> collection);
}
