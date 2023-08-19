package com.gerardo_steven.services;

import com.gerardo_steven.NotificationException;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

@Service
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String email, String message) throws NotificationException {
        try{

            /*  
             * Logic to connect to Mailing provider to send the message ....
             */

            // Persists the message in a log file
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String log = "[" + timeStamp + "] - Email message sent to " + email + " => " + message;
            try {
                FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(log);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }catch(Exception e){
            throw new NotificationException("Error sending Email notification to " + email, e);
        }
    }
}