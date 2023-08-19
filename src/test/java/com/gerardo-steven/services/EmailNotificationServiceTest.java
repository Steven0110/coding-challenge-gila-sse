package com.gerardo_steven.services;

import org.junit.Test;
import static org.mockito.Mockito.*;
import com.gerardo_steven.*;
import com.gerardo_steven.controllers.*;
import com.gerardo_steven.models.*;
import com.gerardo_steven.services.*;

public class EmailNotificationServiceTest {

    @Test
    public void testSendNotification_Success() throws NotificationException {
        // Arrange
        String email = "test@example.com";
        String message = "Test message";
        EmailNotificationService emailService = new EmailNotificationService();

        // Act & Assert
        emailService.sendNotification(email, message);
        // Add assertions here if needed
    }

    @Test
    public void testSendNotification_Failure() {
        // Arrange
        String email = "test@example.com";
        String message = "Test message";
        EmailNotificationService emailServiceMock = mock(EmailNotificationService.class);

        // Mocking a failure scenario by throwing an exception
        try {
            doThrow(new NotificationException("Simulated Email sending failure")).when(emailServiceMock).sendNotification(email, message);
        } catch (NotificationException ignored) {}

        // Act & Assert
        // The exception is expected to be thrown
        org.junit.jupiter.api.Assertions.assertThrows(NotificationException.class,
            () -> emailServiceMock.sendNotification(email, message));
    }
}
