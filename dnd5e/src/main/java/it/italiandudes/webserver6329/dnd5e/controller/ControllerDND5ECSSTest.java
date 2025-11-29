package it.italiandudes.webserver6329.dnd5e.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class ControllerDND5ECSSTest {

    @GetMapping("/dnd5e/css_test")
    private String css_test() {
        return "dnd5e/css_test";
    }
}
