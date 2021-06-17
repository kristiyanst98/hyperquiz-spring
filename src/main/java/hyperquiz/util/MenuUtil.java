package hyperquiz.util;

import hyperquiz.exceptions.LoginFailException;
import hyperquiz.model.*;

import java.io.*;
import java.util.*;

public class MenuUtil {
    public static final String SELECT_OPTION = "Select an option:";
    private static final String EXIT_OPTION = "<0> Exit";
    public static final String LOGIN_OPTION = "<1> Login";
    public static final String REGISTER_OPTION = "<2> Register";
    public static final String PRINT_USER_OPTION = "<1> Print Users";
    public static final String PRINT_QUIZ_OPTION = "<2> Print Quizzes";
    public static final String CREATE_QUIZ_OPTION ="<3> Create Quiz";
    public static final String PLAY_QUIZ_OPTION = "<4> Play Quiz";
    private static Map<Integer, String> options = new LinkedHashMap<>();
    private static ObjectInputStream IN;
    private static Map<Integer, String> loggedOpt = new LinkedHashMap<>();
    private static List<User> allUsers=readUsers();


    public static void printMenu() {
        options.put(0, EXIT_OPTION);
        options.put(1, LOGIN_OPTION);
        options.put(2, REGISTER_OPTION);

        Scanner scanner = new Scanner(System.in);
        boolean flag=true;
        while (flag) {
            System.out.println(SELECT_OPTION);
            for (Map.Entry<Integer, String> opt : options.entrySet()) {
                System.out.println(opt.getValue());
            }
            int num = scanner.nextInt();
            switch (num) {
                case 0:
                    System.out.println("Quitting...");
                    return;
                case 1:
                    login();
                    flag=false;
                    break;
                case 2:
                    StreamUtil.createUser(UserUtil.createUser());
                    break;
                case 4:
                    printUsers();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void printQuizzes() {
        List<PrintUtil.ColumnDescriptor> metadataColumns = List.of(
                new PrintUtil.ColumnDescriptor("created", "Created", 10, Alignment.CENTER),
                new PrintUtil.ColumnDescriptor("updated", "Updated", 10, Alignment.CENTER)
        );

        List<PrintUtil.ColumnDescriptor> quizColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("id", "ID", 5, Alignment.CENTER),
                new PrintUtil.ColumnDescriptor("title", "Title", 8, Alignment.LEFT),
                new PrintUtil.ColumnDescriptor("author", "Author", 14, Alignment.LEFT),
                new PrintUtil.ColumnDescriptor("description", "Description", 20, Alignment.LEFT),
                new PrintUtil.ColumnDescriptor("expectedDuration", "Duration", 10, Alignment.RIGHT, 2),
                new PrintUtil.ColumnDescriptor("tags", "Tags", 11, Alignment.CENTER)
        ));

        quizColumns.addAll(metadataColumns);
        String quizReport = PrintUtil.formatTable(quizColumns, readQuizzes(), "Quiz List:");
        System.out.println(quizReport);

    }

    public static void printUsers() {
        List<PrintUtil.ColumnDescriptor> metadataColumns = List.of(
                new PrintUtil.ColumnDescriptor("created", "Created", 10, Alignment.CENTER),
                new PrintUtil.ColumnDescriptor("updated", "Updated", 10, Alignment.CENTER)
        );

        List<PrintUtil.ColumnDescriptor> userColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("username", "Username", 10, Alignment.CENTER),
                new PrintUtil.ColumnDescriptor("email", "E-mail", 10, Alignment.LEFT),
                new PrintUtil.ColumnDescriptor("gender", "Gender", 8, Alignment.LEFT),
                new PrintUtil.ColumnDescriptor("description", "Description", 20, Alignment.LEFT),
                new PrintUtil.ColumnDescriptor("status", "Status", 5, Alignment.RIGHT, 2)
        ));


        userColumns.addAll(metadataColumns);
        String userReport = PrintUtil.formatTable(userColumns, allUsers, "User List:");
        System.out.println(userReport);
    }
    public static void printPlayers(Quiz quiz) {
        List<Player> onlyForQuiz=new ArrayList<>();
        for(User user:allUsers){
            if(user.getRole()==Role.PLAYER){
                if(user.getQuizzes().contains(quiz)){
                    onlyForQuiz.add((Player)user);
                }
            }
        }
        List<PrintUtil.ColumnDescriptor> metadataColumns = List.of(
                new PrintUtil.ColumnDescriptor("created", "Created", 10, Alignment.CENTER),
                new PrintUtil.ColumnDescriptor("updated", "Updated", 10, Alignment.CENTER)
        );

        List<PrintUtil.ColumnDescriptor> userColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("username", "Username", 10, Alignment.CENTER),
                new PrintUtil.ColumnDescriptor("rank", "Rank", 10, Alignment.LEFT),
                new PrintUtil.ColumnDescriptor("gender", "Gender", 8, Alignment.LEFT),
                new PrintUtil.ColumnDescriptor("overallScore", "Score", 20, Alignment.LEFT)
        ));

        userColumns.addAll(metadataColumns);
        String userReport = PrintUtil.formatTable(userColumns, onlyForQuiz, "Player List:");
        System.out.println(userReport);
    }

    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        Object obj;
        try {
            IN = new ObjectInputStream(new FileInputStream("Entities.data"));
            while ((obj = IN.readObject()) != null) {
                if (obj instanceof User) {
                    users.add((User) obj);
                }
            }
        } catch (IOException | ClassNotFoundException e) {

        }
        return users;
    }

    public static List<Quiz> readQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();

        Object obj;
        try {
            IN=new ObjectInputStream(new FileInputStream("Entities.data"));
            while ((obj = IN.readObject()) != null) {
                if (obj instanceof Quiz) {
                    quizzes.add((Quiz) obj);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
        }

        return quizzes;
    }


    public static void login() {
        allUsers=readUsers();
        Scanner scanner = new Scanner(System.in);
        boolean flag=true;
        while (flag) {
            System.out.println("Enter username or 0 to quit:");
            String username = scanner.nextLine();
            if (username.equals("0")) {
                break;
            } else {
                try {
                    for (User user : allUsers) {
                        if (user.getUsername().equals(username)) {
                            System.out.println("Enter password:");
                            String password = scanner.nextLine();
                            if (password.equals(user.getPassword())) {
                                System.out.println("Login successful!");
                                flag=false;
                                printLoggedOpt(user);
                            } else {
                                throw new LoginFailException("Login failed");

                            }
                        }
                    }
                }catch (LoginFailException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void printLoggedOpt(User user) {
        Scanner scanner = new Scanner(System.in);
        if (user.getRole() == Role.PLAYER) {
            loggedOpt.put(0, EXIT_OPTION);
            loggedOpt.put(1, PRINT_USER_OPTION);
            loggedOpt.put(2, PRINT_QUIZ_OPTION);
            loggedOpt.put(3, CREATE_QUIZ_OPTION);
            loggedOpt.put(4, PLAY_QUIZ_OPTION);
            while (true) {
                System.out.println(SELECT_OPTION);
                for (Map.Entry<Integer, String> opt : loggedOpt.entrySet()) {
                    System.out.println(opt.getValue());
                }
                int num = scanner.nextInt();
                switch (num) {
                    case 0:
                        System.out.println("Quitting...");
                        return;
                    case 1:
                        printUsers();
                        break;
                    case 2:
                        printQuizzes();
                        break;
                    case 3:
                        StreamUtil.createQuiz(QuizUtil.createQuiz());
                        break;
                    case 4:
                        playQuiz((Player) user);
                        break;
                    default:
                        System.out.println("Invalid Option");

                }
            }
        }
    }
    public static void playQuiz(Player user) {
            List<Quiz> quizzes = readQuizzes();
            Scanner scanner = new Scanner(System.in);
            Quiz pickedQuiz = new Quiz();
            QuizResult quizResult=new QuizResult();
            boolean flag = false;
            System.out.println("Pick a quiz by its ID:");
            for (Quiz quiz : quizzes) {
                System.out.println(quiz.getId() + ". " + quiz.getTitle());
            }
            long num = scanner.nextInt();
            scanner.nextLine();
            for (Quiz quiz : quizzes) {
                if (num == quiz.getId()) {
                    pickedQuiz = quiz;
                    quizResult.setQuiz(pickedQuiz);
                    quizResult.setPlayer(user);
                    flag = true;
                }
            }
            while (flag) {
                for (Question q : pickedQuiz.getQuestions()) {
                    System.out.println(q.getText());
                    String answer = scanner.nextLine();
                    for (Answer a : q.getAnswers()) {
                        if (a.getText().equalsIgnoreCase(answer)) {
                            quizResult.addScore(a.getScore());
                        }
                    }
                }

                flag = false;
        }
        user.getQuizzes().add(pickedQuiz);
        user.getResults().add(quizResult);
        user.getOverallScore();
        printPlayers(pickedQuiz);

    }

}
