package it.italiandudes.webserver6329.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class ControllerLogin {

    // Methods
    @GetMapping("/login")
    public String formLogin() {
        return "web/login";
    }
}
