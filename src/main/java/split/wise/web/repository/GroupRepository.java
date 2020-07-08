package split.wise.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Group;

import java.util.List;


public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByName(String name);

}
