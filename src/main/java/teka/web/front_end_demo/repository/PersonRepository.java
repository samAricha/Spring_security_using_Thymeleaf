package teka.web.front_end_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teka.web.front_end_demo.model.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);
    Boolean existsByUsername(String Username);

    Optional<Person> findPersonByIdNumber(int idNumber);
    Boolean existsPersonByIdNumber(int idNumber);
}
