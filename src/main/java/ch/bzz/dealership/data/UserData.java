package ch.bzz.dealership.data;

import ch.bzz.dealership.model.User;
import ch.bzz.dealership.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that reads, writes and finds users
 * @author Nadim Bhatti
 * @since 28-06-2022
 * @version 1.0
 */
public class UserData {
    private static final UserData instance = new UserData();

    /**
     * finds a user by its username and password
     * @param username
     * @param password
     * @return user
     */
    public static User findUser(String username, String password){
        User user = new User();
        List<User> userList = readJson();

        for(User entry: userList){
            if(entry.getUsername().equals(username) &&
                entry.getPassword().equals(password)){
                user = entry;
            }
        }
        return user;
    }

    /**
     * reads the json file as a list
     * @return user list
     */
    private static List<User> readJson(){
        List<User> userList = new ArrayList<>();

        try{
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("userJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user: users){
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
