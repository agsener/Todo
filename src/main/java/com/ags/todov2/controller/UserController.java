package com.ags.todov2.controller;

import com.ags.todov2.dto.GenericResponse;
import com.ags.todov2.dto.LoginDto;
import com.ags.todov2.model.User;
import com.ags.todov2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.ags.todov2.controller.TaskController.LOGGEDIN_USER;


@RestController
@RequestMapping("login")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private GenericResponse login(@RequestBody LoginDto request,
                                  HttpSession session) {
        User user = userService.login(request.getUsername(), request.getPassword());

        if(user != null){
            session.setAttribute(LOGGEDIN_USER, user);
            return new GenericResponse()
                    .setCode(0);
        }else{
            return new GenericResponse()
                    .setCode(50);
        }
    }

    @PostMapping("logout")
    private GenericResponse logout(HttpSession session){
        session.removeAttribute(LOGGEDIN_USER);
        return new GenericResponse()
                .setCode(0);
    }

}
