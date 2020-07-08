package split.wise.web.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.*;
import split.wise.web.repository.ExpenseRepository;
import split.wise.web.service.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ExpenseImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private UsersService usersService;

    @Autowired
    private DebtService debtService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private GroupBalanceService groupBalanceService;

    @Autowired
    private ExpenseDebtsService expenseDebtsService;

    @Autowired
    private RepaymentsService repaymentsService;

    @Autowired
    private MailService mailService;

    public Expense save(Expense expense) {
        return expenseRepo.save(expense);
    }

    @Override
    public Expense saveNewExpense(Expense expense) {
        expense.setDeleted(false);
        if (expense.getGroup().getId() == null)
            expense.setGroup(null);
        Expense expense1 = expenseRepo.save(expense);
        if (expense1.getGroup() != null) {
            expense1.getContributors().forEach((contributor -> {
                GroupBalance groupBalance = groupBalanceService.get(contributor.getUser(), expense1.getGroup());
                System.out.println(groupBalance.getAmount());
                groupBalance.setAmount(groupBalance.getAmount() + contributor.getNetBalance());
                groupBalanceService.save(groupBalance);
                contributor.setExpense(expense1);
                expenseDebtsService.save(contributor);
            }));
        }
        expense1.getRepayments().forEach((repayment -> {
            Users user1 = usersService.get(repayment.getFrom().getId()),
                    user2 = usersService.get(repayment.getTo().getId());
            user1.setMoney(user1.getMoney() - repayment.getAmount());
            user2.setMoney(user2.getMoney() + repayment.getAmount());
            usersService.save(user1);
            usersService.save(user2);
            Debt debt1 = debtService.get(repayment.getFrom(), repayment.getTo(), expense1.getGroup());
            debt1.setAmount(debt1.getAmount() + repayment.getAmount());
            debtService.save(debt1);
            Debt debt2 = debtService.get(repayment.getTo(), repayment.getFrom(), expense1.getGroup());
            debt2.setAmount(debt2.getAmount() - repayment.getAmount());
            debtService.save(debt2);
            repayment.setExpense(expense1);
            repaymentsService.save(repayment);
            if (expense1.getGroup() != null) {
                Debt debt3 = debtService.get(repayment.getFrom(), repayment.getTo(), null);
                debt3.setAmount(debt3.getAmount() + repayment.getAmount());
                debtService.save(debt3);
                Debt debt4 = debtService.get(repayment.getTo(), repayment.getFrom(), null);
                debt4.setAmount(debt4.getAmount() - repayment.getAmount());
                debtService.save(debt4);
            }

        }));

        Activity activity = new Activity();
        activity.setAction("added");
        activity.setExpense(expense1);
        activityService.save(activity);
        System.out.println(expense1.getDescription());
        if(expense1.getCreationMethod().equals("settleUp"))
        {
            mailService.settleUpEmail(expense1);
            System.out.println("enter in");
        }

        else
        mailService.sendExpenseEmail(expense1, "just added ");

        return expenseRepo.getOne(expense1.getId());
    }

    public List<Expense> get() {
        return expenseRepo.findAll();
    }

    @Override
    public Expense get(Long id) {
        return expenseRepo.getOne(id);
    }

    @Override
    public List<Expense> getAll() {
        return expenseRepo.findAll();
    }

    @Override
    public List<Expense> getAllUsersInvolvedExpensesByGroup(Users from, Users to, Group group) {
        List<Expense> expenses = new ArrayList<>();
        List<Repayments> repayments = repaymentsService.getAllUsersInvolvedRepaymentsByGroup(from, to, group);
        if (repayments == null)
            return null;
        repayments.forEach((payment -> {
            expenses.add(payment.getExpense());
        }));
        return expenses;
    }

    @Override
    public List<Expense> getAllUserInvolvedExpenses(Users user) {
        List<Expense> userInvolvedExpenses = new ArrayList<>();
        List<ExpenseDebts> expenseDebts = expenseDebtsService.getAllByUser(user);
        if (expenseDebts == null)
            return null;
        expenseDebts.forEach((expenseDebt -> {
            userInvolvedExpenses.add(expenseDebt.getExpense());
        }));
        return userInvolvedExpenses;
    }

    @Override
    public Expense edit(Expense expense) {
        expense.setDeleted(false);
        if (expense.getGroup().getId() == null)
            expense.setGroup(null);
        Expense expense1 = expenseRepo.getOne(expense.getId());
        if (expense1.getGroup() != null) {
            expense1.getContributors().forEach((contributor -> {
                GroupBalance groupBalance = groupBalanceService.get(contributor.getUser(), expense1.getGroup());
                groupBalance.setAmount(groupBalance.getAmount() - contributor.getNetBalance());
                groupBalanceService.save(groupBalance);
            }));
        }
        expenseDebtsService.deleteByExpenseId(expense.getId());
        if (expense.getGroup() != null) {
            expense.getContributors().forEach((contributor -> {
                GroupBalance groupBalance = groupBalanceService.get(contributor.getUser(), expense.getGroup());
                System.out.println(groupBalance);
                System.out.println(groupBalance.getAmount());
                groupBalance.setAmount(groupBalance.getAmount() - contributor.getNetBalance());
                groupBalanceService.save(groupBalance);
                contributor.setExpense(expense);
                expenseDebtsService.save(contributor);
            }));
        }
        expense1.getRepayments().forEach((repayment -> {
            Users user1 = usersService.get(repayment.getFrom().getId()),
                    user2 = usersService.get(repayment.getTo().getId());
            user1.setMoney(user1.getMoney() + repayment.getAmount());
            user2.setMoney(user2.getMoney() - repayment.getAmount());
            usersService.save(user1);
            usersService.save(user2);
            Debt debt1 = debtService.get(repayment.getFrom(), repayment.getTo(), expense1.getGroup());
            debt1.setAmount(debt1.getAmount() - repayment.getAmount());
            debtService.save(debt1);
            Debt debt2 = debtService.get(repayment.getTo(), repayment.getFrom(), expense1.getGroup());
            debt2.setAmount(debt2.getAmount() + repayment.getAmount());
            debtService.save(debt2);
            if (expense1.getGroup() != null) {
                Debt debt3 = debtService.get(repayment.getFrom(), repayment.getTo(), null);
                debt3.setAmount(debt3.getAmount() - repayment.getAmount());
                debtService.save(debt3);
                Debt debt4 = debtService.get(repayment.getTo(), repayment.getFrom(), null);
                debt4.setAmount(debt4.getAmount() + repayment.getAmount());
                debtService.save(debt4);
            }
        }));
        repaymentsService.deleteByExpenseId(expense1.getId());
        expense.getRepayments().forEach((repayment -> {
            Users user1 = usersService.get(repayment.getFrom().getId()),
                    user2 = usersService.get(repayment.getTo().getId());
            user1.setMoney(user1.getMoney() - repayment.getAmount());
            user2.setMoney(user2.getMoney() + repayment.getAmount());
            usersService.save(user1);
            usersService.save(user2);
            Debt debt1 = debtService.get(repayment.getFrom(), repayment.getTo(), expense.getGroup());
            debt1.setAmount(debt1.getAmount() + repayment.getAmount());
            debtService.save(debt1);
            Debt debt2 = debtService.get(repayment.getTo(), repayment.getFrom(), expense.getGroup());
            debt2.setAmount(debt2.getAmount() - repayment.getAmount());
            debtService.save(debt2);
            repayment.setExpense(expense);
            repaymentsService.save(repayment);
            if (expense1.getGroup() != null) {
                Debt debt3 = debtService.get(repayment.getFrom(), repayment.getTo(), null);
                debt3.setAmount(debt3.getAmount() + repayment.getAmount());
                debtService.save(debt3);
                Debt debt4 = debtService.get(repayment.getTo(), repayment.getFrom(), null);
                debt4.setAmount(debt4.getAmount() - repayment.getAmount());
                debtService.save(debt4);
            }
        }));
        expenseRepo.save(expense);
        Activity activity = new Activity();
        activity.setAction("edited");
        activity.setExpense(expenseRepo.getOne(expense.getId()));
        activityService.save(activity);
        return expenseRepo.getOne(expense1.getId());
    }

    @Override
    public void delete(Long id) {
        Expense expense = expenseRepo.getOne(id);
        mailService.sendExpenseEmail(expense, "just deleted ");
        if (expense.getGroup() != null) {
            expense.getContributors().forEach((contributor -> {
                GroupBalance groupBalance = groupBalanceService.get(contributor.getUser(), expense.getGroup());
                groupBalance.setAmount(groupBalance.getAmount() - contributor.getNetBalance());
                groupBalanceService.save(groupBalance);
            }));
        }
        expense.getRepayments().forEach((repayment -> {
            Users user1 = repayment.getFrom(), user2 = repayment.getTo();
            user1.setMoney(user1.getMoney() + repayment.getAmount());
            user2.setMoney(user2.getMoney() - repayment.getAmount());
            usersService.save(user1);
            usersService.save(user2);
            Debt debt1 = debtService.get(repayment.getFrom(), repayment.getTo(), expense.getGroup());
            debt1.setAmount(debt1.getAmount() - repayment.getAmount());
            debtService.save(debt1);
            Debt debt2 = debtService.get(repayment.getTo(), repayment.getFrom(), expense.getGroup());
            debt2.setAmount(debt2.getAmount() + repayment.getAmount());
            debtService.save(debt2);
            if (expense.getGroup() != null) {
                Debt debt3 = debtService.get(repayment.getFrom(), repayment.getTo(), null);
                debt3.setAmount(debt3.getAmount() - repayment.getAmount());
                debtService.save(debt3);
                Debt debt4 = debtService.get(repayment.getTo(), repayment.getFrom(), null);
                debt4.setAmount(debt4.getAmount() + repayment.getAmount());
                debtService.save(debt4);
            }
        }));
        expense.setDeleted(true);
        expenseRepo.save(expense);
        Activity activity = new Activity();
        activity.setAction("deleted");
        activity.setExpense(expense);
        activityService.save(activity);
    }

    @Override
    public void undelete(Long id) {
        Expense expense = expenseRepo.getOne(id);
        if (expense.getGroup() != null) {
            expense.getContributors().forEach((contributor -> {
                GroupBalance groupBalance = groupBalanceService.get(contributor.getUser(), expense.getGroup());
                groupBalance.setAmount(groupBalance.getAmount() + contributor.getNetBalance());
                groupBalanceService.save(groupBalance);
            }));
        }
        expense.getRepayments().forEach((repayment -> {
            Users user1 = repayment.getFrom(), user2 = repayment.getTo();
            user1.setMoney(user1.getMoney() - repayment.getAmount());
            user2.setMoney(user2.getMoney() + repayment.getAmount());
            usersService.save(user1);
            usersService.save(user2);
            Debt debt1 = debtService.get(repayment.getFrom(), repayment.getTo(), expense.getGroup());
            debt1.setAmount(debt1.getAmount() + repayment.getAmount());
            debtService.save(debt1);
            Debt debt2 = debtService.get(repayment.getTo(), repayment.getFrom(), expense.getGroup());
            debt2.setAmount(debt2.getAmount() - repayment.getAmount());
            debtService.save(debt2);
            if (expense.getGroup() != null) {
                Debt debt3 = debtService.get(repayment.getFrom(), repayment.getTo(), null);
                debt3.setAmount(debt3.getAmount() + repayment.getAmount());
                debtService.save(debt3);
                Debt debt4 = debtService.get(repayment.getTo(), repayment.getFrom(), null);
                debt4.setAmount(debt4.getAmount() - repayment.getAmount());
                debtService.save(debt4);
            }
        }));
        expense.setDeleted(false);
        expenseRepo.save(expense);
        Activity activity = new Activity();
        activity.setAction("undeleted");
        activity.setExpense(expense);
        activityService.save(activity);
        mailService.sendExpenseEmail(expense, "just updated ");
    }

}
