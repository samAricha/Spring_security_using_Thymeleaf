package teka.web.front_end_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teka.web.front_end_demo.config.CustomUserDetails;
import teka.web.front_end_demo.dto.LoginDto;
import teka.web.front_end_demo.dto.RegisterDto;
import teka.web.front_end_demo.model.Person;
import teka.web.front_end_demo.model.Roles;
import teka.web.front_end_demo.repository.PersonRepository;
import teka.web.front_end_demo.repository.RolesRepository;

import java.util.Collections;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private PersonRepository personRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          PersonRepository personRepository, RolesRepository rolesRepository,
                          PasswordEncoder passwordEncoder) {

        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "registration";
    }


    @PostMapping("register")
    public ResponseEntity<String> register(@ModelAttribute("registerDto") RegisterDto registerDto){

        if(personRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Person person = new Person();
        person.setUsername(registerDto.getUsername());
        person.setFirstName(registerDto.getFirstName());
        person.setLastName(registerDto.getLastName());
        person.setIdNumber(registerDto.getIdNumber());
        person.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Roles roles = rolesRepository.findByName("PERSON").get();
        person.setRoles(Collections.singletonList(roles));

        personRepository.save(person);

        return new ResponseEntity<>("User registration success!", HttpStatus.OK);

    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @PostMapping("login")
    public ResponseEntity<String> login(@ModelAttribute("loginDto") LoginDto loginDto){

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                                loginDto.getPassword()));

       SecurityContextHolder.getContext().setAuthentication(authentication);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = null;
        if (auth != null) {
            Object principal = auth.getPrincipal();
            System.out.println("Principal class: " + principal.getClass().getName());
            if (principal instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) principal;
                userId = userDetails.getPerson().getId();
                System.out.println(userId);
            } else {
                System.out.println("Principal is not an instance of CustomUserDetails: " + principal.getClass().getName());
            }
        }



        return new  ResponseEntity<>(userId.toString(), HttpStatus.BAD_REQUEST);

    }

}
