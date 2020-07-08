package split.wise.web.service;

import split.wise.web.model.Expense;
import split.wise.web.model.Group;
import split.wise.web.model.Repayments;
import split.wise.web.model.Users;

import java.util.List;

public interface RepaymentsService {

    Repayments save(Repayments repayment);

    Repayments save(Expense expense, Users fromUser, Users toUser, Long amount);

    List<Repayments> getAllUsersInvolvedRepaymentsByGroup(Users from, Users to, Group group);

    void deleteByExpenseId(Long id);

}
