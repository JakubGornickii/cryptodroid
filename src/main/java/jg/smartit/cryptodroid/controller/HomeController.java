package jg.smartit.cryptodroid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model) {
        String greet = "Hello World!";
        model.addAttribute("greet", greet);
        return "index";
    }


}
