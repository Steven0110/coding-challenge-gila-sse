package com.gerardo_steven.services;

import org.springframework.stereotype.Service;
import java.util.List;
import com.gerardo_steven.models.User;
import com.gerardo_steven.services.*;
import com.gerardo_steven.NotificationException;

@Service
public class NotificationController {

    private SmsNotificationService smsService;
    private EmailNotificationService emailService;
    private PushNotificationService pushService;

    public NotificationController() {
        this.smsService = new SmsNotificationService();
        this.emailService = new EmailNotificationService();
        this.pushService = new PushNotificationService();
    }

    public int sendNotifications(List<User> users, String message) {
        int errors = 0;
        for (User user : users) {
            errors += sendNotificationForUser(user, message); //Count all messages which failed to be sent
        }

        return errors;
    }

    private int sendNotificationForUser(User user, String message){
        int errors = 0;

        if (user.hasSMS()) {
            try{
                smsService.sendNotification(user.getPhone(), message);
            }catch(NotificationException e){
                errors++;
            }
        }
        if (user.hasEmail()) {
            try{
                emailService.sendNotification(user.getEmail(), message);
            }catch(NotificationException e){
                errors++;
            }
        }
        if (user.hasPush()) {
            try{
                pushService.sendNotification(user.getDeviceToken(), message);
            }catch(NotificationException e){
                errors++;
            }
        }

        return errors;
    }
}
