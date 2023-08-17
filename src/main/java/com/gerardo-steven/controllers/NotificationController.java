package com.gerardo_steven.controllers;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

import java.text.SimpleDateFormat;
import com.gerardo_steven.models.User;

public class NotificationController {

    private static final String LOG_FILE_PATH = "messages_log.txt";

	public static void sendNotification(User user, String message) {
		if( user.hasSMS() )
			sendSMS(user.getPhone(), message);

		if( user.hasEmail() )
			sendEmail(user.getEmail(), message);

		if( user.hasPush() )
			sendPush(user.getDeviceToken(), message);
	}

	public static void sendSMS(String phone, String message) {

        // Persists the message in a log file
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String log = "[" + timeStamp + "] - SMS message sent to " + phone + " => " + message;
        writeLog(log);
	}

	public static void sendEmail(String email, String message) {

        // Persists the message in a log file
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String log = "[" + timeStamp + "] - Email message sent to " + email + " => " + message;
        writeLog(log);
	}

	public static void sendPush(String deviceToken, String message) {
        // Persists the message in a log file
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String log = "[" + timeStamp + "] - Push message sent to " + deviceToken + " => " + message;
        writeLog(log);

	}

    // Method used to log sent messages 
    public static void writeLog(String log) {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(log);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}