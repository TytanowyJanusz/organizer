package com.lepczynski.hubert.organizer.Services;

import com.lepczynski.hubert.organizer.Models.DTOs.UserDTO;
import com.lepczynski.hubert.organizer.Models.Task;
import com.lepczynski.hubert.organizer.Models.User;
import com.lepczynski.hubert.organizer.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService
{
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> GetAllUsers()
    {
        return userRepository.findAll();
    }

    public boolean saveNewUser(UserDTO userDTO)
    {
        User newUser = new User(userDTO.getUsername(), "password", userDTO.getEmail());
        userRepository.save(newUser);
        return true;
    }

    public User getUserById(Long id)
    {
        return userRepository.findById(id).get();
    }

    //responsibility of TaskService TODO
    boolean AddTaskToUser(Long userId, Task task)
    {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            user.getTasks().add(task);
            return true;
        }
        else
            return false;
    }

}
