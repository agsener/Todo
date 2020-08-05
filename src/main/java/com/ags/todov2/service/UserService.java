package com.ags.todov2.service;

import com.ags.todov2.dto.UserDto;
import com.ags.todov2.model.User;
import com.ags.todov2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.ags.todov2.controller.TaskController.LOGGEDIN_USER;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Return:
     * 0 -> success
     * 50 -> Cannot find user
     */
    public User login(String username, String password) {
        return findByUsername(username);
    }

    public User save(UserDto userDto, HttpSession httpSession){
        User dbUser = userRepository.findById(((User) httpSession.getAttribute(LOGGEDIN_USER)).getId()).orElse(null);
        dbUser.setUsername(userDto.getUsername());
        dbUser.setName(userDto.getName());
        dbUser.setSurname(userDto.getSurname());
        userRepository.save(dbUser);
        return dbUser;
    }
}

