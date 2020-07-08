package split.wise.web.service;

import split.wise.web.model.Expense;
import split.wise.web.model.Group;
import split.wise.web.model.Users;

import java.util.List;

public interface ExpenseService {

    Expense save(Expense expense);

    Expense saveNewExpense(Expense expense);

    Expense get(Long id);

    List<Expense> getAll();

    List<Expense> getAllUsersInvolvedExpensesByGroup(Users from, Users to, Group group);

    List<Expense> getAllUserInvolvedExpenses(Users user);

    Expense edit(Expense expense);

    void delete(Long id);

    void undelete(Long id);

}
