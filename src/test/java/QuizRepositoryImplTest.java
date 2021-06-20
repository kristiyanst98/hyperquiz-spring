//import hyperquiz.dao.QuizRepository;
//import hyperquiz.dao.impl.LongKeyGenerator;
//import hyperquiz.exceptions.EntityAlreadyExistsException;
//import hyperquiz.model.Quiz;
//import hyperquiz.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.testng.annotations.Test;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class QuizRepositoryImplTest {
//
//    private static final List<Quiz> QUIZZES = List.of(
//            new Quiz("quiz1", new User(), "quiz@A1",5,"#tag1"),
//            new Quiz("quiz2", new User(), "quiz@A2",4,"#tag2"),
//            new Quiz("quiz3", new User(), "quiz@A3",1,"#tag3"),
//            new Quiz("quiz4", new User(), "quiz@A4",2,"#tag4"),
//            new Quiz("quiz5", new User(), "quiz@A5",3,"#tag5")
//    );
//    private QuizRepository qr;
//    private LongKeyGenerator keyGenerator;
//
//    @BeforeEach
//    public void setUp(){
//        keyGenerator=new LongKeyGenerator();
//        qr=new QuizRepositoryImpl(keyGenerator);
//    }
//
//    private void fillInQuizzes() {
//        QUIZZES.forEach((u) -> {
//                    try {
//                        qr.create(u);
//                    } catch (EntityAlreadyExistsException e) {
//                        e.printStackTrace();
//                    }
//                }
//        );
//    }
//
//    @Test
//    void findByExpectedDuration() {
//        fillInQuizzes();
//        Optional<Quiz> quiz = qr.findByExpectedDuration(5);
//        Optional<Quiz> quiz1=Optional.ofNullable(QUIZZES.get(0));
//        assertNotEquals(null,quiz,"Quiz found by expected duration is null");
//        assertEquals(quiz,quiz1,"Quizzes compared not equal");
//    }
//
//    @Test
//    void findByTitle() {
//        fillInQuizzes();
//        Optional<Quiz> quiz = qr.findByTitle("quiz4");
//        Optional<Quiz> quiz1=Optional.ofNullable(QUIZZES.get(3));
//        assertNotEquals(null,quiz,"Quiz found by title is null");
//        assertEquals(quiz,quiz1,"Quizzes compared not equal");
//
//    }
//
//    @Test
//    void findByDescription() {
//        fillInQuizzes();
//        Optional<Quiz> quiz = qr.findByDescription("quiz@A3");
//        Optional<Quiz> quiz1=Optional.ofNullable(QUIZZES.get(2));
//        assertNotEquals(null,quiz,"Quiz found by description is null");
//        assertEquals(quiz,quiz1,"Quizzes compared not equal");
//    }
//
//    @Test
//    void findByTags() {
//        fillInQuizzes();
//        Optional<Quiz> quiz = qr.findByTags("#tag1");
//        assertNotEquals(null,quiz,"Quiz found by tags is null");
//    }
//
//    @Test
//    void findByTTD() {
//        fillInQuizzes();
//        Set<Quiz> quiz = qr.findByTTD("quiz3");
//        Set<Quiz> quiz1=Set.of(QUIZZES.get(2));
//        assertNotEquals(null,quiz,"Set of quizzes is null");
//        assertEquals(quiz.size(),quiz1.size(),"Count not the same");
//        assertTrue(quiz.contains(QUIZZES.get(2)),"Quiz Set does not contain the expected quiz");
//    }
//}