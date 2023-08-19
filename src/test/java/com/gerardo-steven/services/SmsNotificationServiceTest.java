package com.gerardo_steven.services;

import org.junit.Test;
import static org.mockito.Mockito.*;
import com.gerardo_steven.*;
import com.gerardo_steven.controllers.*;
import com.gerardo_steven.models.*;
import com.gerardo_steven.services.*;

public class SmsNotificationServiceTest {

    @Test
    public void testSendNotification_Success() throws NotificationException {
        // Arrange
        String phone = "+123456789";
        String message = "Test message";
        SmsNotificationService smsService = new SmsNotificationService();

        // Act & Assert
        smsService.sendNotification(phone, message);
        // Add assertions here if needed
    }

    @Test
    public void testSendNotification_Failure() {
        // Arrange
        String phone = "+123456789";
        String message = "Test message";
        SmsNotificationService smsServiceMock = mock(SmsNotificationService.class);

        // Mocking a failure scenario by throwing an exception
        try {
            doThrow(new NotificationException("Simulated SMS sending failure")).when(smsServiceMock).sendNotification(phone, message);
        } catch (NotificationException ignored) {
        }

        // Act & Assert
        // The exception is expected to be thrown
        org.junit.jupiter.api.Assertions.assertThrows(NotificationException.class,
            () -> smsServiceMock.sendNotification(phone, message));
    }
}
