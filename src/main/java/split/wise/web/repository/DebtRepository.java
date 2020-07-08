package split.wise.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Debt;
import split.wise.web.model.Group;
import split.wise.web.model.Users;

public interface DebtRepository extends JpaRepository<Debt,Long> {

    Debt findByFromUserAndToUserAndGroup(Users user, Users toUser, Group group);

}
