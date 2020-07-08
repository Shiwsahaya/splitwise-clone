package split.wise.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Group;
import split.wise.web.model.GroupBalance;
import split.wise.web.model.Users;


public interface GroupBalanceRepository extends JpaRepository<GroupBalance, Long> {

    GroupBalance findByUserAndGroup(Users user, Group group);

}
