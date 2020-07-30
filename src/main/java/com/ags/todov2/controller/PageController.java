package com.ags.todov2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "{id}"})
    public String index() {
        return "index";
    }
}
