package com.ags.todov2.service;

import com.ags.todov2.dto.UserDto;
import com.ags.todov2.model.User;
import com.ags.todov2.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

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

    public User save(UserDto userDto, HttpSession httpSession) {
        String a = System.getProperty("user.dir");
        User dbUser = userRepository.findById(((User) httpSession.getAttribute(LOGGEDIN_USER)).getId()).orElse(null);
        dbUser.setUsername(userDto.getUsername());
        dbUser.setName(userDto.getName());
        dbUser.setSurname(userDto.getSurname());

        if (userDto.getBase64() != null) {
            byte[] decodedBytes = Base64.getDecoder().decode(userDto.getBase64());
            String uuid = UUID.randomUUID().toString();
            try {
                FileUtils.writeByteArrayToFile(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\3rd\\profilPictures\\" + uuid + ".jpg"), decodedBytes);
                dbUser.setProfilPictureUuid(uuid);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userRepository.save(dbUser);
        return dbUser;
    }
}

