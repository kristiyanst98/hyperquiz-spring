package hyperquiz.services.impl;

import hyperquiz.dao.UserRepository;
import hyperquiz.model.User;
import hyperquiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository ur;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return ur.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByID(Long id) {
        return ur.findById(id).get();
    }

    @Override
    public User addUser(User user) {
        return ur.create(user);
    }

    @Override
    public User updateUser(User user) {
        return ur.update(user);
    }

    @Override
    public User deleteUserByID(Long id) {
        return ur.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countUsers() {
        return ur.count();
    }

    @Override
    public List<User> createUserBatch(Collection<User> collection) {
        return ur.createBatch(collection);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return ur.findByUsername(username);
    }
}
