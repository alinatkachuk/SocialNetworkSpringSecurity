package com.alinatkachuk.socialnetwork.controller;

import com.alinatkachuk.socialnetwork.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("admin/api/v1/users")
public class AdminController {

    private static final List<User> USERS = Arrays.asList(
            new User (1L, "Alina"),
            new User (2L, "Nikita")
    );

    @GetMapping
    public static List<User> getAllUsers() {
        System.out.println("getAllUsers");
        return USERS;
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        System.out.println("registerNewUser");
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable ("userId") Long userId) {
        System.out.println("deleteUser");
        System.out.println(userId);
    }

    @PutMapping
    public void updateUser(@PathVariable ("userId")Long userId, @RequestBody User user) {
        System.out.println("updateUser");
        System.out.println(String.format("%s %s", userId, user));
    }
}
