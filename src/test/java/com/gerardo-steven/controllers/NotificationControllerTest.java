package com.gerardo_steven.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.gerardo_steven.*;
import com.gerardo_steven.controllers.*;
import com.gerardo_steven.models.*;
import com.gerardo_steven.services.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @Mock
    private SmsNotificationService smsService;

    @Mock
    private EmailNotificationService emailService;

    @Mock
    private PushNotificationService pushService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendNotifications() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setPhone("+1234567890");
        user1.setEmail("user1@example.com");
        user1.setDeviceToken("deviceToken1");
        user1.setChannels(new String[]{"sms", "email", "push"});
        users.add(user1);

        User user2 = new User();
        user2.setPhone("+9876543210");
        user2.setChannels(new String[]{"sms"});
        users.add(user2);

        String message = "Test message";

        int errors = 0;

        try{
            doNothing().when(smsService).sendNotification(eq("+1234567890"), eq(message));
            doThrow(NotificationException.class).when(emailService).sendNotification(eq("user1@example.com"), eq(message));
            doNothing().when(pushService).sendNotification(eq("deviceToken1"), eq(message));
            doThrow(NotificationException.class).when(smsService).sendNotification(eq("+9876543210"), eq(message));

            errors = notificationController.sendNotifications(users, message);
        }catch(NotificationException e){
            e.printStackTrace();
        }

        try{
            verify(smsService, times(1)).sendNotification(eq("+1234567890"), eq(message));
        }catch(NotificationException e){}
        try{
            verify(emailService, times(1)).sendNotification(eq("user1@example.com"), eq(message));
        }catch(NotificationException e){}
        try{
            verify(pushService, times(1)).sendNotification(eq("deviceToken1"), eq(message));
        }catch(NotificationException e){}
        try{
            verify(smsService, times(1)).sendNotification(eq("+9876543210"), eq(message));
        }catch(NotificationException e){}

        // Ensure that errors returned match the expected number of exceptions thrown
        assert errors == 2;
    }
}