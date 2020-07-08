package split.wise.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.ExpenseDebts;
import split.wise.web.model.Users;
import split.wise.web.repository.ExpenseDebtsRepository;
import split.wise.web.service.ExpenseDebtsService;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ExpenseDebtsImpl implements ExpenseDebtsService {

    @Autowired
    private ExpenseDebtsRepository expenseDebtsRepo;

    public ExpenseDebts save(ExpenseDebts expenseDebts){
        return expenseDebtsRepo.save(expenseDebts);
    }

    @Override
    public List<ExpenseDebts> getAllByUser(Users user) {
        return expenseDebtsRepo.findAllByUser(user);
    }

    @Override
    public void deleteByExpenseId(Long id) {
        expenseDebtsRepo.deleteAllByExpenseId(id);
    }

}
