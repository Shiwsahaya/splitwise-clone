package split.wise.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Group;
import split.wise.web.model.Repayments;
import split.wise.web.model.Users;

import java.util.List;

public interface RepaymentsRepository extends JpaRepository<Repayments, Long> {

    void deleteAllByExpenseId(Long id);

    List<Repayments> getAllByFromAndToAndExpense_Group(Users from, Users to, Group group);

}
