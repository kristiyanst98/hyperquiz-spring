package hyperquiz.dao;

import hyperquiz.model.User;

public interface UserRepository extends Repository <Long, User> {
    User findByUsername(String username);
}
