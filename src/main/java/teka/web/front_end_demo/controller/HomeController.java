package teka.web.front_end_demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import teka.web.front_end_demo.config.CustomUserDetails;
import teka.web.front_end_demo.model.Person;
import teka.web.front_end_demo.repository.PersonRepository;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/home")
    public String home() {
        // Add any model attributes here
        return "home";
    }

    @PostMapping("/submitForm")
    public ResponseEntity<String> submitForm(HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");



        return new ResponseEntity<>("current id:" +userId, HttpStatus.BAD_REQUEST);
    }

}
