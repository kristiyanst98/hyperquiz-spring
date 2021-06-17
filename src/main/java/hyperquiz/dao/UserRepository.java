package hyperquiz.dao;

import hyperquiz.model.User;

import java.util.Optional;

public interface UserRepository extends Repository <Long, User> {
    Optional<User> findByUsername(String username);
}
