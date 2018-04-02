package co.lilpilot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by lilpilot on 2018/4/2.
 */
@Controller
public class TestController {

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("name", "World");
        return "test";
    }
}
