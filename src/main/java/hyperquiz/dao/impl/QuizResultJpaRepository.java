package hyperquiz.dao.impl;

import hyperquiz.dao.QuizResultRepository;
import hyperquiz.exceptions.EntityAlreadyExistsException;
import hyperquiz.exceptions.EntityCreationException;
import hyperquiz.exceptions.EntityDataInvalidException;
import hyperquiz.model.QuizResult;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class QuizResultJpaRepository implements QuizResultRepository {


    @PersistenceContext
    EntityManager em;

    @Override
    public List<QuizResult> findAll() {
        return em.createQuery("SELECT q FROM QuizResult AS q").getResultList();
    }

    @Override
    public Optional<QuizResult> findById(Long id) throws EntityDataInvalidException {
        Optional<QuizResult> quizFound = Optional.ofNullable(em.find(QuizResult.class, id));
        if (quizFound.isPresent()) {
            return quizFound;
        }
        throw new EntityDataInvalidException("Error finding quiz result with ID: " + id);
    }

    @Override
    public QuizResult create(QuizResult entity) throws EntityAlreadyExistsException {
        em.persist(entity);
        return entity;
    }

    @Override
    public QuizResult update(QuizResult entity) {
        Optional<QuizResult> old = findById(entity.getId());
        if (old.isEmpty()) {
            throw new EntityNotFoundException(String.format("Entity with ID='%s' does not exist.", entity.getId()));
        }
        QuizResult result = em.merge(entity);
        return result;
    }

    @Override
    public QuizResult deleteById(Long id) throws EntityDataInvalidException {
        Optional<QuizResult> quizToDelete = findById(id);
        if (quizToDelete.isPresent()) {
            em.remove(quizToDelete.get());
        }
        return quizToDelete.get();
    }

    @Override
    public long count() {
        return (Long) em.createQuery("SELECT COUNT(q) FROM QuizResult q").getSingleResult();
    }

    @Override
    public List<QuizResult> createBatch(Collection<QuizResult> collection) throws EntityCreationException {
        List<QuizResult> allQuizResults = new ArrayList<>();
        for (QuizResult u : collection) {
            em.persist(u);
            allQuizResults.add(u);
        }
        return allQuizResults;
    }
}
