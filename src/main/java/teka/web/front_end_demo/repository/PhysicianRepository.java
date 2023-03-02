package teka.web.front_end_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teka.web.front_end_demo.model.Physician;

public interface PhysicianRepository extends JpaRepository<Physician, Long> {
}
