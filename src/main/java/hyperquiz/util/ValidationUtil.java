package hyperquiz.util;

import hyperquiz.exceptions.InvalidGenderException;
import hyperquiz.model.Gender;
import hyperquiz.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Locale;

public class ValidationUtil {

    public static boolean validateString(String string, int min, int max) {

        return (string.trim().length() >= min && string.trim().length() <= max);
    }

    public static boolean validateNumber(int number) {
        return number >= 0;
    }

    public static boolean validateNumber(int number, int min, int max) {
        int cnt = 1;
        int tmp = number;
        while (tmp != 0) {
            tmp /= 10;
            cnt++;
        }
        if (cnt >= min && cnt <= max) {
            return true;
        } else {
            return false;
        }
    }

    public static Gender validateGender(String s) throws InvalidGenderException {

        String upper = s.toUpperCase(Locale.ROOT);
        if (upper.equals("M") || upper.equals("MALE")) {
            return Gender.MALE;
        } else if (upper.equals("F") || upper.equals("FEMALE")) {
            return Gender.FEMALE;
        } else {
            throw new InvalidGenderException("Enter a valid gender");
        }

    }

    public static boolean validateEmail(String s) {
//        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(s);
//        return matcher.matches();
        return true;
    }


    public static boolean validatePassword(String s) {
//        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(s);
//        return matcher.matches();
        return true;
    }

    public static boolean validateStatus(User user, String s) {
        String upper = s.toUpperCase(Locale.ROOT);
        if (upper.equals("Y") || upper.equals("YES")) {
            user.setStatus(true);
            return true;
        } else if (upper.equals("N") || upper.equals("NO")) {
            user.setStatus(false);
            return true;
        }
        return false;
    }

    public static boolean validateTags(String s) {
        return s.contains("#");
    }

    public static boolean validateUser(String username) {
        try (ObjectInputStream IN = new ObjectInputStream(new FileInputStream("Entities.data"))) {
            Object object;
            while ((object = IN.readObject()) != null) {
                if (object instanceof User) {
                    if (((User) object).getUsername().equals(username)) {
                        return true;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
        }
        return false;
    }


}
