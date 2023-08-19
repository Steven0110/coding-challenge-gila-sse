package com.gerardo_steven.services;

import com.gerardo_steven.NotificationException;

public interface NotificationService {
    static final String LOG_FILE_PATH = "src/main/log/messages_log.txt";

    void sendNotification(String target, String message) throws NotificationException;
}