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
import org.springframework.validation.BindingResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import com.gerardo_steven.models.User;
import com.gerardo_steven.controllers.NotificationController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class RestApiApp {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(RestApiApp.class, args);
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendMessage(@Validated @RequestBody RequestData requestData, BindingResult bindingResult) {
        
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
        ObjectMapper objectMapper = new ObjectMapper();
        try{
             List<User> users = objectMapper.readValue(new File("users.json"), new TypeReference<List<User>>() {});

             // Filters users subscribed to the given category 
             List<User> filteredUsers = users.stream()
                    .filter(user -> user.getSubscribedTopics() != null && user.isSubscribedToTopic(category))
                    .collect(Collectors.toList());

             for(User user : filteredUsers) {
                NotificationController.sendNotification(user, message);
             }

             return ResponseEntity.ok(new ApiResponse("Message sent successfully"));

        } catch (IOException exception) { // Handle errores when processing
            try{
                PrintStream ps = new PrintStream(new FileOutputStream("errors.log", true));
                exception.printStackTrace(ps);
                ps.close();
            }catch(FileNotFoundException fileException){
                fileException.printStackTrace();
            }finally{
                return ResponseEntity.internalServerError().body(new ApiResponse("Error processing your request."));
            }
        }
    }


    // Class for mapping input JSON
    static class RequestData {

        private String category;
        private String message;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // Class for mapping output JSON
    static class ApiResponse {
        private String response;

        public ApiResponse(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
    }
}
