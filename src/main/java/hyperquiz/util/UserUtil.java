package hyperquiz.util;

import hyperquiz.exceptions.InvalidGenderException;
import hyperquiz.model.*;

import java.util.Scanner;

public class UserUtil {

    public static User createUser() {
        Scanner scanner = new Scanner(System.in);
        User user = new Player();
        System.out.println("=NOW CREATING A NEW USER=");
            do {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                if (ValidationUtil.validateString(username, 2, 15)) {
                    if (!ValidationUtil.validateUser(username)) {
                        user.setUsername(username);
                        break;
                    }
                else {
                        System.out.println("User with username: " + username + " already exists");
                    }
                } else {
                    System.out.println("Username must contain from 2 to 15 characters");
                }
            } while (true);
            do {
                System.out.println("Enter e-mail:");
                String mail = scanner.nextLine();
                if (ValidationUtil.validateEmail(mail)) {
                    user.setEmail(mail);
                    break;
                } else {
                    System.out.println("Enter a valid email");
                }
            } while (true);
            do {
                System.out.println("Enter password:");
                String pw = scanner.nextLine();
                if (ValidationUtil.validatePassword(pw)) {
                    user.setPassword(pw);
                    break;
                } else {
                    System.out.println("The password must have minimum 8 characters, 1 capital letter, 1 special symbol");
                }
            } while (true);
            do {
                System.out.println("Enter a gender: M / F");
                String gender = scanner.nextLine();
                try {
                    user.setGender(ValidationUtil.validateGender(gender));
                } catch (InvalidGenderException e) {
                    System.out.println(e.getMessage());
                }
            } while (user.getGender() == null);
            do {
                System.out.println("Enter a user description:");
                String desc = scanner.nextLine();
                if (ValidationUtil.validateString(desc, 2, 250)) {
                    user.setDescription(desc);
                    break;
                } else {
                    System.out.println("Description should be between 20 and 250 characters");
                }
            } while (true);
            do {
                System.out.println("Enter metadata:");
                String meta = scanner.nextLine();
                if (ValidationUtil.validateString(meta, 0, 512)) {
                    user.setMetadata(meta);
                    break;
                }
            } while (true);
            do {
                System.out.println("Should the account be active(Y/N):");
                String status = scanner.nextLine();
                if (ValidationUtil.validateStatus(user, status)) {
                    break;
                } else {
                    System.out.println("Enter a valid status");
                }
            } while (true);

        return user;
    }
}
