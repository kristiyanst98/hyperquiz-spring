package hyperquiz.services;

import hyperquiz.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User findUserByID(Long id);

    public User addUser(User user);

    public User updateUser(User user);

    public User deleteUserByID(Long id);

    public long countUsers();

    public List<User> createUserBatch(Collection<User> collection);

    public User findUserByUsername(String username);
}