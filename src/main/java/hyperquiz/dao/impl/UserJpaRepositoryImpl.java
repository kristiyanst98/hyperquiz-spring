package hyperquiz.dao.impl;

import hyperquiz.dao.UserRepository;
import hyperquiz.exceptions.EntityAlreadyExistsException;
import hyperquiz.exceptions.EntityCreationException;
import hyperquiz.exceptions.EntityDataInvalidException;
import hyperquiz.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserJpaRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager em;

//    public void init(){
//        emf= Persistence.createEntityManagerFactory("hyperQuizPU");
//        em=emf.createEntityManager();
//    }
    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User AS u").getResultList();
    }

    @Override
    public Optional<User> findById(Long id) throws EntityDataInvalidException {
        Optional<User> userFound=Optional.ofNullable(em.find(User.class,id));
        if(userFound.isPresent()){
            return userFound;
        }
        throw new EntityDataInvalidException("Error finding user with ID: "+id);
    }

    @Override
    public User create(User entity) throws EntityAlreadyExistsException {
        em.persist(entity);
        return entity;
    }

    @Override
    public User update(User entity){
        Optional<User> old = findById(entity.getId());
        if (old.isEmpty()) {
            throw new EntityNotFoundException(String.format("Entity with ID='%s' does not exist.", entity.getId()));
        }
        User result = em.merge(entity);
        return result;
    }

    @Override
    public User deleteById(Long id) throws EntityDataInvalidException {
        Optional<User> userToDelete = findById(id);
        if (userToDelete.isPresent()) {
            em.remove(userToDelete.get());
        }
        return userToDelete.get();
    }
    @Override
    public long count() {
        return (Long) em.createQuery("SELECT COUNT(u) FROM User u").getSingleResult();
    }

    @Override
    public List<User> createBatch(Collection<User> collection) throws EntityCreationException {
        List<User> allUsers = new ArrayList<>();
        for (User u : collection) {
            em.persist(u);
            allUsers.add(u);
        }
        return allUsers;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst();
        }
    }
