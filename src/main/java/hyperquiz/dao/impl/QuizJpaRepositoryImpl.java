package hyperquiz.dao.impl;

import hyperquiz.dao.QuizRepository;
import hyperquiz.exceptions.EmptyEntityCollection;
import hyperquiz.exceptions.EntityAlreadyExistsException;
import hyperquiz.exceptions.EntityCreationException;
import hyperquiz.exceptions.EntityDataInvalidException;
import hyperquiz.model.Quiz;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Transactional
public class QuizJpaRepositoryImpl implements QuizRepository {

    @PersistenceContext
    EntityManager em;

    //    public void init(){
//        emf= Persistence.createEntityManagerFactory("hyperQuizPU");
//        em=emf.createEntityManager();
//    }
    @Override
    public List<Quiz> findAll() {
        return em.createQuery("SELECT q FROM Quiz AS q").getResultList();
    }

    @Override
    public Optional<Quiz> findById(Long id) throws EntityDataInvalidException {
        Optional<Quiz> quizFound = Optional.ofNullable(em.find(Quiz.class, id));
        if (quizFound.isPresent()) {
            return quizFound;
        }
        throw new EntityDataInvalidException("Error finding quiz with ID: " + id);
    }

    @Override
    public Quiz create(Quiz entity) throws EntityAlreadyExistsException {
        em.persist(entity);
        return entity;
    }

    @Override
    public Quiz update(Quiz entity) {
        Optional<Quiz> old = findById(entity.getId());
        if (old.isEmpty()) {
            throw new EntityNotFoundException(String.format("Entity with ID='%s' does not exist.", entity.getId()));
        }
        Quiz result = em.merge(entity);
        return result;
    }

    @Override
    public Quiz deleteById(Long id) throws EntityDataInvalidException {
        Optional<Quiz> quizToDelete = findById(id);
        if (quizToDelete.isPresent()) {
            em.remove(quizToDelete.get());
        }
        return quizToDelete.get();
    }

    @Override
    public long count() {
        return (Long) em.createQuery("SELECT COUNT(q) FROM Quiz q").getSingleResult();
    }

    @Override
    public List<Quiz> createBatch(Collection<Quiz> collection) throws EntityCreationException {
        List<Quiz> allQuizzes = new ArrayList<>();
        for (Quiz u : collection) {
            em.persist(u);
            allQuizzes.add(u);
        }
        return allQuizzes;
    }

    @Override
    public Set<Quiz> findByExpectedDuration(int expectedDuration) {
        TypedQuery<Quiz> query = em.createQuery(
                "SELECT q FROM Quiz q WHERE q.expectedDuration=" + expectedDuration, Quiz.class);
        if (query.getResultList().isEmpty()) {
            throw new EntityDataInvalidException("Error finding quiz with duration: " + expectedDuration);
        }
        return query.getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Set<Quiz> findByTitle(String title) {
        TypedQuery<Quiz> query = em.createQuery(
                "SELECT q FROM Quiz q WHERE q.title LIKE '%"+title+"%'", Quiz.class);
        if(query.getResultList().isEmpty()){
            throw new EmptyEntityCollection("No existing entities with title: "+title);
        }
        return query.getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Set<Quiz> findByDescription(String description) {
        TypedQuery<Quiz> query = em.createQuery(
                "SELECT q FROM Quiz q WHERE q.description LIKE '%"+description+"%'", Quiz.class);
        if(query.getResultList().isEmpty()){
            throw new EmptyEntityCollection("No existing entities with description: "+description);
        }
        return query.getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Set<Quiz> findByTags(String tags) {
        TypedQuery<Quiz> query = em.createQuery(
                "SELECT q FROM Quiz q WHERE q.tags LIKE '%"+tags+"%'", Quiz.class);
        if(query.getResultList().isEmpty()){
            throw new EmptyEntityCollection("No existing entities with tags: "+tags);
        }
        return query.getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Set<Quiz> findByTTD(String criterias) {
        TypedQuery<Quiz> query = em.createQuery(
                "SELECT q FROM Quiz q WHERE q.description LIKE '%"+criterias+"%' OR q.title LIKE '%"+criterias+"%' OR q.tags LIKE '%"+criterias+"%'", Quiz.class);

        if(query.getResultList().isEmpty()){
            throw new EmptyEntityCollection("No еxisting entities found while searching with the word: "+criterias);
        }
        return query.getResultStream().collect(Collectors.toSet());
    }
}
