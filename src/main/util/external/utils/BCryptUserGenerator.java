package external.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BCryptUserGenerator {
    private static final List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Scanner scanner = new Scanner(System.in);

        System.out.println("BCryptUserGenerator");
        while (true) {
            User user = new User();
            System.out.print("Username: ");

            String username = scanner.nextLine();
            checkExit(username);
            user.username = username;

            System.out.print("Password: ");
            String password = scanner.nextLine();
            checkExit(password);
            user.password = password;

            user.hash = encoder.encode(password);

            users.add(user);
        }
    }

    private static void checkExit(String string) {
        if (string.isEmpty() || string.isBlank()) {
            System.out.println();
            System.out.println();

            for (User user : users) {
                System.out.printf("insert into users(username, password) values('%s', '%s'); -- %s\n", user.username, user.hash, user.password);
            }

            System.exit(0);
        }
    }

    private static class User {
        String username;
        String password;
        String hash;
    }
}
