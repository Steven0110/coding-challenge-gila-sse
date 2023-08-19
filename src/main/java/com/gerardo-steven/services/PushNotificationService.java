package com.gerardo_steven.services;

import com.gerardo_steven.NotificationException;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

@Service
public class PushNotificationService implements NotificationService {
    @Override
    public void sendNotification(String deviceToken, String message) throws NotificationException {
        try{

            /*  
             * Logic to connect to Push notification provider to send the message ....
             */

            // Persists the message in a log file
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String log = "[" + timeStamp + "] - Push notification sent to " + deviceToken + " => " + message;
            try {
                FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(log);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }catch(Exception e){
            throw new NotificationException("Error sending Push notification to " + deviceToken, e);
        }
    }
}