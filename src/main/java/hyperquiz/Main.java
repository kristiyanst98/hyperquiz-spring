package hyperquiz;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        Quiz quiz = new Quiz();
//        Quiz quiz2 = new Quiz();
//        User user = new User();
//        user.setUsername("Georgi Ivanov");
//        quiz.setAuthor(user);
//        quiz.setDescription("Easy one");
//        quiz.setExpectedDuration(1);
//        quiz.setId(1L);
//        quiz.setTitle("Easy");
//        User user2=new User();
//        user2.setUsername("John Doe");
//        quiz2.setAuthor(user2);
//        quiz2.setDescription("Getting complicated");
//        quiz2.setId(2L);
//        quiz2.setTitle("Medium");

//        QuizRepository quizRepository = new QuizRepositoryImpl(new LongKeyGenerator());
//        UserRepository userRepository = new UserRepositoryMemoryImpl(new LongKeyGenerator());
//        String quizReport = PrintUtil.formatTable(quizColumns, Arrays.asList(quizz), "Quiz List:");
//        System.out.println(quizReport);
//        MenuUtil.printMenu();


//       String path = Main.class.getClassLoader().getResource("database.properties").getPath();
//       Properties props = new Properties();
//       props.load(new FileInputStream(path));
//
//        try{
//            Class.forName(props.getProperty("db.driver"));
//            System.out.println("DB driver loaded");
//        }catch (ClassNotFoundException e) {
//            System.out.println("DB Driver not found");
//            e.printStackTrace();
//            return;
//        }
//
//            try(Connection connection = DriverManager.getConnection(props.getProperty("db.url"),props)){
//                System.out.printf("Successfully connected to: %s%n",props.getProperty("db.url"));
//                //4. Prepare statement
////                PreparedStatement statement= connection.prepareStatement("SELECT * FROM users");
////                ResultSet results = statement.executeQuery();
////                while(results.next()){
////                    User user = new User(results.getLong("id"),results.getString("username"),results.getString("email"),results.getString("password"),Gender.valueOf(results.getString("gender")), Role.valueOf(results.getString("role")),results.getURL("picture"),results.getString("description"),results.getString("metadata"),results.getBoolean("status"));
////                    System.out.println(user.forDB());
////
////                }
//            }catch (SQLException throwables){
//                throwables.printStackTrace();
//            }
//
//
//    }
//    }

//    public static final List<PrintUtil.ColumnDescriptor> USER_COLUMNS = List.of(
//            new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
//            new PrintUtil.ColumnDescriptor("username", "Username", 15, LEFT),
//            new PrintUtil.ColumnDescriptor("email", "Email", 15, LEFT),
//            new PrintUtil.ColumnDescriptor("password", "Password", 20, LEFT),
//            new PrintUtil.ColumnDescriptor("gender", "Gender", 8, RIGHT, 2),
//            new PrintUtil.ColumnDescriptor("role", "Role", 10, CENTER),
//            new PrintUtil.ColumnDescriptor("description", "Description", 15, CENTER),
//            new PrintUtil.ColumnDescriptor("status", "Status", 5, CENTER),
//            new PrintUtil.ColumnDescriptor("created", "Created", 19, CENTER),
//            new PrintUtil.ColumnDescriptor("updated", "Updated", 19, CENTER)
//    );
//
//    public static void main(String[] args) throws Exception {
//        URL myURL = new URL("http://example.com/pages/");
//
//
//        User[] users = {
//                new User(1L,"user1", "user1@abv.bg", "@User1HasAStrongPassword", Gender.MALE, Role.PLAYER,myURL, "what is this description I dont know", "01x2", true),
//                new User(2L,"user2", "user2@abv.bg", "@User2HasAStrongPassword", Gender.MALE, Role.PLAYER, myURL,"what is this description I dont know", "02x3", true),
//                new User(3L,"user3", "user3@abv.bg", "@User3HasAStrongPassword", Gender.MALE, Role.PLAYER, myURL,"what is this description I dont know", "03x4", true)
//
//        };
//
//        UserRepository ur = new UserJpaRepositoryImpl();
//        ((UserJpaRepositoryImpl) ur).init();
//
//        System.out.println("There are currently: " + ur.count() + " users");
//        if (ur.count() == 0) {
//            List<User> created = new ArrayList<>();
//            try {
//                created = ur.createBatch(Arrays.asList(users));
//            } catch (EntityCreationException e) {
//                e.printStackTrace();
//            }
//
//            String usersRep = PrintUtil.formatTable(USER_COLUMNS, created, "Created User List:");
//            System.out.println(usersRep);
//        }
//
////        ur.create(users[2]);
//        String userReport = PrintUtil.formatTable(USER_COLUMNS, ur.findAll(), "Created User List:");
//        System.out.println(userReport);

//        User deleteUser = ur.deleteById(3L);
//        System.out.println("Deleted user: "+deleteUser.getId());
//        String afterDeleteReport = PrintUtil.formatTable(USER_COLUMNS, ur.findAll(), "After delete User List:");
//        System.out.println(afterDeleteReport);
//
//        Optional<User> nonExistingUser=ur.findByUsername("user1");
//        if(nonExistingUser.isPresent()){
//            System.out.println(nonExistingUser.get());
//        }else{
//            System.out.println("User with username: user1 doesn't exist");
//        }


//        Optional<User> user=ur.findById(1L);
//        if(user.isPresent()) {
//            user.get().setUsername("updatedUser");
//            ur.update(user.get());
//            String updatedReport = PrintUtil.formatTable(USER_COLUMNS, ur.findAll(), "Updated User List:");
//            System.out.println(updatedReport);
//        }
}
}
