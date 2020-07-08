package split.wise.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Users;

import java.util.List;


public interface UsersRepository  extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    List<Users> findAllByName(String name);

}
