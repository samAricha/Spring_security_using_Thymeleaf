package teka.web.front_end_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teka.web.front_end_demo.model.Person;
import teka.web.front_end_demo.service.PersonService;

@RestController
@Controller
@RequestMapping("/v1/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public void createPerson(@RequestBody Person person){

        personService.registerPerson(person);

    }
}
