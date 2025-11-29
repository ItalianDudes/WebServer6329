package it.italiandudes.webserver6329.dnd5e.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class ControllerDND5EIndex {

    @GetMapping("/dnd5e")
    private String index() {
        return "dnd5e/index";
    }
}
