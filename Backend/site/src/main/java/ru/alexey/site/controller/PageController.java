package ru.alexey.site.controller;
/* 
05.04.2022: Alexey created this file inside the package: ru.alexey.site.controller 
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping(path = "login")
    public String getLogin(){
        return "login";
    }

    @GetMapping(path = "feed")
    public String getFeed(){
        return "feed";
    }
}
