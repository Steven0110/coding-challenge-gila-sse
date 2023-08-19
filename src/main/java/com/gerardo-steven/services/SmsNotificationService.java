package com.gerardo_steven.services;

import com.gerardo_steven.NotificationException;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

@Service
public class SmsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String phone, String message) throws NotificationException{
        try{

            /*  
             * Logic to connect to SMS provider to send the message ....
             */

            // Hardcoded failure, to simulate an error when sending an SMS
            //if( phone.equals("+525512341234")){
            //    throw new Exception();
            //}else{


            // Persists the message in a log file
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String log = "[" + timeStamp + "] - SMS message sent to " + phone + " => " + message;
            try {
                FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(log);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //}

        }catch(Exception e){
            throw new NotificationException("Error sending SMS notification to " + phone, e);
        }
    }
}