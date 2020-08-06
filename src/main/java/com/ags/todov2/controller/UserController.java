package com.ags.todov2.controller;

import com.ags.todov2.dto.GenericResponse;
import com.ags.todov2.dto.LoginDto;
import com.ags.todov2.dto.UserDto;
import com.ags.todov2.model.User;
import com.ags.todov2.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static com.ags.todov2.controller.TaskController.LOGGEDIN_USER;


@RestController
@RequestMapping("login")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("profile")
    private User findUser(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(LOGGEDIN_USER);
        return user;
    }

    @GetMapping("{uuid}")
    private GenericResponse convertUuidToBase64(@PathVariable("uuid") String uuid) {

        byte[] fileContent;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\3rd\\profilPictures\\" + uuid + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            return new GenericResponse()
                    .setCode(20);
        }
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return new GenericResponse()
                .setCode(0)
                .setMessage(encodedString);
    }

    @PostMapping
    private GenericResponse login(@RequestBody LoginDto request,
                                  HttpSession session) {
        User user = userService.login(request.getUsername(), request.getPassword());

        if (user != null) {
            session.setAttribute(LOGGEDIN_USER, user);
            return new GenericResponse()
                    .setCode(0);
        } else {
            return new GenericResponse()
                    .setCode(50);
        }
    }

    @PostMapping("logout")
    private GenericResponse logout(HttpSession httpSession) {
        httpSession.removeAttribute(LOGGEDIN_USER);
        return new GenericResponse()
                .setCode(0);
    }

    @PostMapping("update")
    private GenericResponse update(@RequestBody UserDto request, HttpSession httpSession) {
        User updatedUser = userService.save(request, httpSession);
        if (updatedUser != null) {
            httpSession.setAttribute(LOGGEDIN_USER, updatedUser);
            return new GenericResponse()
                    .setCode(0);
        } else {
            return new GenericResponse()
                    .setCode(40);
        }
    }
}
