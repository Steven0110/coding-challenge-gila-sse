package com.gerardo_steven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import java.io.*;
import java.util.Date;
import java.util.List;

import com.gerardo_steven.models.User;
import com.gerardo_steven.models.ApiResponse;
import com.gerardo_steven.models.RequestData;
import com.gerardo_steven.services.*;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class RestApiApp {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(RestApiApp.class, args);
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendMessage(@Validated @RequestBody RequestData requestData) {
        
        String category = requestData.getCategory();
        String message = requestData.getMessage();
        
        if(!category.equals("sports") && !category.equals("finance") && !category.equals("films")){ // Invalid category parameter
            String errorMessage = "Wrong request body. Invalid 'category' parameter, it should be either 'sports', 'finance' or 'films'";
            return ResponseEntity.badRequest().body(new ApiResponse(errorMessage));
        }

        if( message.equals("") ){ // Invalid message parameter
            String errorMessage = "Wrong request body. 'message' parameter is required";
            return ResponseEntity.badRequest().body(new ApiResponse(errorMessage));
        }


        // Gets list of users subscribed to the given category - Emulates a Database extraction from a JSON-based DB
        UserService userService = new UserService();
        List<User> filteredUsers = userService.getUsersSubscribedToCategory(category); //Simulates DB extraction

        NotificationController notificationController = new NotificationController();
        int errors = notificationController.sendNotifications(filteredUsers, message);

        if( errors == 0)
            return ResponseEntity.ok(new ApiResponse("Message sent successfully to all subscribers' channels"));
        else
            return ResponseEntity.ok(new ApiResponse("Message sent successfully to all subscribers' channels except " + errors));
    }
}
