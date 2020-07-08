package split.wise.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.*;
import split.wise.web.repository.ActivityRepository;
import split.wise.web.service.ActivityService;
import split.wise.web.service.ExpenseService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@Transactional
public class ActivityImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private ExpenseService expenseService;

    @Override
    public void save(Activity activity) {
        activityRepo.save(activity);
    }

    @Override
    public List<Activity> getAllByUser(Users user) {
        List<Activity> activities = new ArrayList<>();
        List<Expense> expenses = expenseService.getAllUserInvolvedExpenses(user);
        if (expenses != null) {
            expenses.forEach((expense -> {
                activities.addAll(expense.getActivities());
            }));
        }
        if (user.getContributingGroups() != null) {
            user.getContributingGroups().forEach((group -> {
                activities.addAll(group.getActivities());
            }));
        }
        if (activities.size() != 0)
            activities.sort(Comparator.comparing(Auditable::getCreatedAt));
        return activities;
    }

}
