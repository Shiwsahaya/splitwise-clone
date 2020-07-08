package split.wise.web.service;

import split.wise.web.model.*;

import java.util.List;

public interface StackOverflowResolverService {

    Users resolveUser(Users actualUser);

    List<Users> resolveUsersList(List<Users> actualUsersList);

    Group resolveGroup(Group actualGroup);

    List<Group> resolveGroupList(List<Group> actualList);

    Expense resolveExpense(Expense actualExpense);

    List<Expense> resolveExpenseList(List<Expense> actualExpenseList);

    List<Activity> resolveActivityList(List<Activity> actualActivityList);

    Comment resolveComment(Comment actualComment);

    Category resolveCategory(Category actualCategory);

}
