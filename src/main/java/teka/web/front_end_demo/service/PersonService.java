package teka.web.front_end_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teka.web.front_end_demo.model.Person;
import teka.web.front_end_demo.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public void registerPerson(Person person){
        personRepository.save(person);
    }
}
