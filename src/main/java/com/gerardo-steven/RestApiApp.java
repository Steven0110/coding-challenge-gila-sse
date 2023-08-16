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
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class RestApiApp {

    private static final String LOG_FILE_PATH = "messages_log.txt";

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendMessage(@Validated @RequestBody RequestData requestData, BindingResult bindingResult) {
        
        String category = requestData.getCategory();
        String message = requestData.getMessage();
        
        if(!category.equals("SMS") && !category.equals("Email") && !category.equals("Push")){ // Wrong request body
            String errorMessage = "Wrong request body. Invalid 'category' parameter, it should be either 'SMS', 'Email' or 'Push'";
            return ResponseEntity.badRequest().body(new ApiResponse(errorMessage));
        }


        String response = "Message of type " + category + " was successfully sent";
        
        // Persists the message in a log file
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String log = "[" + timeStamp + "] - " + category + " to be sent: " + message;
        writeLog(log);

        return ResponseEntity.ok(new ApiResponse(response));
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(RestApiApp.class, args);
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


    // Method used to log messages into a static file 
    private static void writeLog(String message) {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(message);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
