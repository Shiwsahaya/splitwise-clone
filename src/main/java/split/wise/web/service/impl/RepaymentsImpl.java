package split.wise.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.Expense;
import split.wise.web.model.Group;
import split.wise.web.model.Repayments;
import split.wise.web.model.Users;
import split.wise.web.repository.RepaymentsRepository;
import split.wise.web.service.RepaymentsService;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class RepaymentsImpl implements RepaymentsService {

    @Autowired
    RepaymentsRepository repaymentsRepo;

    @Override
    public Repayments save(Repayments repayment) {
        return repaymentsRepo.save(repayment);
    }

    @Override
    public Repayments save(Expense expense, Users fromUser, Users toUser, Long amount) {
        Repayments repayment = new Repayments();
        repayment.setAmount(amount);
        repayment.setExpense(expense);
        repayment.setFrom(fromUser);
        repayment.setTo(toUser);
        return repaymentsRepo.save(repayment);
    }

    @Override
    public List<Repayments> getAllUsersInvolvedRepaymentsByGroup(Users from, Users to, Group group) {
        return repaymentsRepo.getAllByFromAndToAndExpense_Group(from, to, group);
    }

    @Override
    public void deleteByExpenseId(Long id) {
            repaymentsRepo.deleteAllByExpenseId(id);
    }

}
