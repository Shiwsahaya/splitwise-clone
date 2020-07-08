package split.wise.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Activity;


public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
