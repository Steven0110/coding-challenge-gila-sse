package com.gerardo_steven.services;

import org.springframework.stereotype.Service;
import com.gerardo_steven.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import com.gerardo_steven.models.User;
import java.io.File;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.io.IOException;

@Service
//Service used to simulate users extraction from DB
public class UserService {
	public UserService () {}

    // Filters users subscribed to the given category 
	public List<User> getUsersSubscribedToCategory(String category) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<User> users = new ArrayList<>();

		try {
	        users = objectMapper.readValue(new File("src/main/resources/users.json"), new TypeReference<List<User>>() {})
	         	.stream()
	                .filter(user -> user.getSubscribedTopics() != null && user.isSubscribedToTopic(category))
	                .collect(Collectors.toList());
		}catch(IOException e){
			System.out.println("Error retrieving users from DB");
			e.printStackTrace();
		}

		return users;
	}
}