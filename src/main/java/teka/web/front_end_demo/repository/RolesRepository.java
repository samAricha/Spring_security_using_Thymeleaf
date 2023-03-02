package teka.web.front_end_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teka.web.front_end_demo.model.Roles;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(String name);

}
