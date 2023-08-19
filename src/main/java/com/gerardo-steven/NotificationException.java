package com.gerardo_steven;

import java.io.PrintStream;
import java.io.FileOutputStream;

public class NotificationException extends Exception {
    public NotificationException(String message, Throwable cause) {
        super(message, cause);

        // Persists notification error in errors log
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("src/main/log/errors.log", true));
            ps.println(message);
            cause.printStackTrace(ps);
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*  Constructor for Unit testing purposes   */
    public NotificationException(String message) {
        super(message);

        // Persists notification error in errors log
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("src/main/log/errors.log", true));
            ps.println(message);
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}