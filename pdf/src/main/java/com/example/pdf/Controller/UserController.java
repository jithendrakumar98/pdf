package com.example.pdf.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pdf.Model.UserModel;
import com.example.pdf.Service.UserService;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/")
    public String hi() {
        return "Hiii";
    }

    @GetMapping("/cal")
    public int cal(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }
    @GetMapping("/emails/{year}")
    public List<String> getEmailsByYear(@PathVariable int year) {
        return userService.getMailsByYear(year);
    }
    @GetMapping("/users/getusers")
    public ArrayList<UserModel> getusers() {
        return (ArrayList<UserModel>) userService.getAllUsers();
    }

    @PostMapping("/users/login")
    public boolean login(@RequestBody UserModel request) {
        return userService.login(request.getUserID(), request.getPassword());
    }

    // Accept image, convert it to byte array, and save it to the database
    @PostMapping("/users/post")
    public ResponseEntity<UserModel> addUser(
            @RequestParam("userID") long userID,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("year") int year,
            @RequestParam("mobileNo") String mobileNo,
            @RequestParam("password") String password,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        byte[] imageBytes = null;
        if (image != null && !image.isEmpty()) {
            try {
                // Convert image to byte array
                imageBytes = image.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        // Save user data, including the image
        UserModel newUser = new UserModel(userID, name, email, year, mobileNo, password, imageBytes);
        UserModel user = userService.saveUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // Handle multiple users with optional images for each
    @PostMapping("/users/postMultiple")
    public ResponseEntity<List<UserModel>> addUsers(
            @RequestParam("userIDs") List<Long> userIDs,
            @RequestParam("names") List<String> names,
            @RequestParam("emails") List<String> emails,
            @RequestParam("years") List<Integer> years,
            @RequestParam("mobileNos") List<String> mobileNos,
            @RequestParam("passwords") List<String> passwords,
            @RequestParam(value = "images", required = false) List<MultipartFile> images) {

        List<UserModel> newUsers = new ArrayList<>();
        for (int i = 0; i < userIDs.size(); i++) {
            long userID = userIDs.get(i);
            String name = names.get(i);
            String email = emails.get(i);
            int year = years.get(i);
            String mobileNo = mobileNos.get(i);
            String password = passwords.get(i);
            MultipartFile image = images != null && images.size() > i ? images.get(i) : null;
            byte[] imageBytes = null;
            if (image != null && !image.isEmpty()) {
                try {
                    imageBytes = image.getBytes();
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
            UserModel newUser = new UserModel(userID, name, email, year, mobileNo, password, imageBytes);
            newUsers.add(newUser);
        }

        List<UserModel> users = userService.saveUsers(newUsers);
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @GetMapping("/users/count")
    public ResponseEntity<Long> getUsersCount() {
        long count = userService.getUsersCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/users/getbyid")
    public ResponseEntity<UserModel> getUserById(@RequestParam Long id) {
        UserModel user = userService.getUserById(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/users/viewImage")
public ResponseEntity<byte[]> viewImageById(@RequestParam Long id) {

    UserModel user = userService.getUserById(id);
    if (user != null && user.getImage() != null) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);  
        headers.setContentLength(user.getImage().length);

        return new ResponseEntity<>(user.getImage(), headers, HttpStatus.OK);
    } else if (user == null) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } else {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

}
