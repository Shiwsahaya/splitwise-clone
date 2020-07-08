package split.wise.web.service;


import split.wise.web.model.ExpenseDebts;
import split.wise.web.model.Group;
import split.wise.web.model.Users;

import java.util.List;


public interface ExpenseDebtsService {

    ExpenseDebts save(ExpenseDebts expenseDebts);

    List<ExpenseDebts> getAllByUser(Users user);

    void deleteByExpenseId(Long id);

}
