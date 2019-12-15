package com.lepczynski.hubert.organizer.Controllers;

import com.lepczynski.hubert.organizer.Models.DTOs.UserDTO;
import com.lepczynski.hubert.organizer.Models.User;
import com.lepczynski.hubert.organizer.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController
{
    @Autowired
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userService.GetAllUsers();
    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }


    @PostMapping("/users")
    public void saveNewUser(@PathVariable UserDTO userDTO)
    {
        userService.saveNewUser(userDTO);
    }
}
