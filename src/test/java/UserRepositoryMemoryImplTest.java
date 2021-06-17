import hyperquiz.dao.UserRepository;
import hyperquiz.dao.impl.LongKeyGenerator;
import hyperquiz.dao.impl.UserRepositoryMemoryImpl;
import hyperquiz.exceptions.EntityAlreadyExistsException;
import hyperquiz.exceptions.EntityDataInvalidException;
import hyperquiz.model.Gender;
import hyperquiz.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryMemoryImplTest {
    private static final List<User> USERS = List.of(
            new User("user1", "user1@abv.bg", "password@A5", Gender.MALE, true),
            new User("user2", "user2@abv.bg", "password@A5", Gender.MALE, true),
            new User("user3", "user3@abv.bg", "password@A5", Gender.FEMALE, true),
            new User("user4", "user4@abv.bg", "password@A5", Gender.MALE, true),
            new User("user5", "user5@abv.bg", "password@A5", Gender.FEMALE, true),
            new User(10L)
    );
    private UserRepository ur;
    private LongKeyGenerator keyGenerator;

    @BeforeEach
    public void setUp(){
        keyGenerator=new LongKeyGenerator();
        ur=new UserRepositoryMemoryImpl(keyGenerator);
    }

    private void fillInUsers() {
        USERS.forEach((u) -> {
                    try {
                        ur.create(u);
                    } catch (EntityAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Test
    void findByUsername() {
        fillInUsers();
        Optional<User> user = ur.findByUsername("user1");
        Optional<User> user1 = Optional.ofNullable(USERS.get(0));
        assertNotEquals(null,user,"User not found");
        assertTrue(user.isPresent(),"User requested isn't present");
        assertTrue(user1.isPresent(),"User requested isn't present");
        assertEquals(user1.get().getUsername(),user.get().getUsername(),"Users' usernames not equal");

    }
    @Test
    void create(){
       assertDoesNotThrow(()->ur.create(USERS.get(2)));
    }
    @Test
    void update(){
        User user = USERS.get(5);
        user.setId(15L);
        fillInUsers();
        assertDoesNotThrow(()->ur.update(user),"Update throws exception");
        assertEquals(USERS.get(5),user,"Users compared not equal");
    }
    @Test
    void findAll(){
        fillInUsers();
        List<User> users=new ArrayList<>(ur.findAll());
        assertNotEquals(null,users,"Cannot extract users from User Repo");
        assertEquals(ur.findAll(),users,"List isn't filled correctly");
        assertEquals(ur.findAll().get(1),users.get(1),"User not the same by list index");
    }
    @Test
    void findById(){
        fillInUsers();
        assertDoesNotThrow(()->ur.findById(2L));
//        Optional<User> user = ur.findById(2L);
//        assertNotEquals(null,user,"User is null");
//        assertEquals(ur.findByUsername("user2"),user);
    }
    @Test
    void deleteById(){
        fillInUsers();
        assertThrows(EntityDataInvalidException.class,()->ur.deleteById(432L));
    }
    @Test
    void count(){
        fillInUsers();
        long count = ur.count();
        assertEquals(USERS.size(),count,"Count method returning wrong size");
    }
}