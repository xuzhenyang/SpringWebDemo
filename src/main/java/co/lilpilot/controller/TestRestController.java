package co.lilpilot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lilpilot on 23/05/2018.
 */
@RestController
public class TestRestController {
    @GetMapping("/rest")
    public String test() {
        return "hello world";
    }
}
