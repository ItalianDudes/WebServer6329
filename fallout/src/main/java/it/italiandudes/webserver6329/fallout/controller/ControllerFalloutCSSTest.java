package it.italiandudes.webserver6329.fallout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class ControllerFalloutCSSTest {

    @GetMapping("/fallout/css_test")
    private String css_test() {
        return "fallout/css_test";
    }
}
