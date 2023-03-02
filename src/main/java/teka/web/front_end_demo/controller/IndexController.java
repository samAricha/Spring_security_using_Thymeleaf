package teka.web.front_end_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("message", "Hello, world!");
        return "footer";
    }

    @GetMapping("/login-page")
    public String login(Model model){
        model.addAttribute("message", "Hello, world!");
        return "login";
    }

    @GetMapping("/register-page")
    public String register(Model model){
        model.addAttribute("message", "Hello, world!");
        return "register";
    }

    @GetMapping("/patient-list")
    public String patients(Model model){
        model.addAttribute("message", "Hello, world!");
        return "patientList";
    }

    @GetMapping("/opd")
    public String opd(Model model){
        model.addAttribute("message", "Hello, world!");
        return "opd2";
    }
}
