package split.wise.web.service.impl;


import org.springframework.stereotype.Service;
import split.wise.web.model.*;
import split.wise.web.service.StackOverflowResolverService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class StackOverflowResolverImpl implements StackOverflowResolverService {

    @Override
    public Users resolveUser(Users actualUser) {
        if(actualUser == null)
            return null;
        System.out.println(1);
        Users resolvedUser = resolveUserWithoutMappings(actualUser);
        System.out.println(2);
        resolvedUser.setMoney(actualUser.getMoney());
        System.out.println(3);
        resolvedUser.setFriends(resolveUsersListWithoutMappings(actualUser.getFriends()));
        System.out.println(4);
        resolvedUser.setContributingGroups(resolveGroupListWithoutMappings(actualUser.getContributingGroups()));
        System.out.println(5);
        resolvedUser.setDebts(resolveDebtList(actualUser.getDebts()));
        System.out.println(actualUser.getNotify());
        resolvedUser.setNotify(resolveNotify(actualUser.getNotify()));
        return resolvedUser;
    }

    @Override
    public List<Users> resolveUsersList(List<Users> actualUsersList) {
        if(actualUsersList == null)
            return null;
        List<Users> resolvedUsersList = new ArrayList<>();
        actualUsersList.forEach((actualUser -> {
            resolvedUsersList.add(resolveUser(actualUser));
        }));
        return resolvedUsersList;
    }

    @Override
    public Group resolveGroup(Group actualGroup) {
        if(actualGroup == null)
            return null;
        Group resolvedGroup = resolveGroupWithoutMappings(actualGroup);
        resolvedGroup.setMembers(resolveUsersListWithoutMappings(actualGroup.getMembers()));
        resolvedGroup.setExpenses(resolveExpenseList(actualGroup.getExpenses()));
        resolvedGroup.setDebts(resolveDebtListWithoutGroup(actualGroup.getDebts()));
        resolvedGroup.setGroupBalances(resolveGroupBalanceList(actualGroup.getGroupBalances()));
        return resolvedGroup;
    }

    @Override
    public List<Group> resolveGroupList(List<Group> actualGroupList) {
        if(actualGroupList == null)
            return null;
        List<Group> resolvedGroupList = new ArrayList<>();
        actualGroupList.forEach((group -> {
            resolvedGroupList.add(resolveGroup(group));
        }));
        return resolvedGroupList;
    }

    @Override
    public Expense resolveExpense(Expense actualExpense) {
        if(actualExpense == null)
            return null;
        Expense resolvedExpense = new Expense();
        resolvedExpense.setId(actualExpense.getId());
        resolvedExpense.setDeleted(actualExpense.getDeleted());
        resolvedExpense.setCreatedAt(actualExpense.getCreatedAt());
        resolvedExpense.setUpdatedAt(actualExpense.getUpdatedAt());
        resolvedExpense.setAmount(actualExpense.getAmount());
        resolvedExpense.setAddedBy(resolveUserWithoutMappings(actualExpense.getAddedBy()));
        resolvedExpense.setGroup(resolveGroupWithoutMappings(actualExpense.getGroup()));
        resolvedExpense.setCategory(resolveCategoryWithoutMappings(actualExpense.getCategory()));
        resolvedExpense.setDescription(actualExpense.getDescription());
        resolvedExpense.setPaymentDate(actualExpense.getPaymentDate());
        resolvedExpense.setCreationMethod(actualExpense.getCreationMethod());
        resolvedExpense.setContributors(resolveExpenseDebtList(actualExpense.getContributors()));
        resolvedExpense.setRepayments(resolveRepaymentList(actualExpense.getRepayments()));
        resolvedExpense.setComments(resolveCommentList(actualExpense.getComments()));
        return resolvedExpense;
    }

    @Override
    public List<Expense> resolveExpenseList(List<Expense> actualExpenseList) {
        if(actualExpenseList == null)
            return null;
        List<Expense> resolvedExpenseList = new ArrayList<>();
        actualExpenseList.forEach((expense -> {
            resolvedExpenseList.add(resolveExpense(expense));
        }));
        return resolvedExpenseList;
    }

    @Override
    public List<Activity> resolveActivityList(List<Activity> actualActivityList) {
        if (actualActivityList == null)
            return null;
        List<Activity> resolvedActivityList = new ArrayList<>();
        actualActivityList.forEach((activity -> {
            Activity activity1 = new Activity();
            activity1.setCreatedAt(activity.getCreatedAt());
            activity1.setAction(activity.getAction());
            activity1.setGroup(resolveGroupWithoutMappings(activity.getGroup()));
            activity1.setExpense(resolveExpense(activity.getExpense()));
            resolvedActivityList.add(activity1);
        }));
        return resolvedActivityList;
    }

    @Override
    public Comment resolveComment(Comment actualComment) {
        Comment resolvedComment = new Comment();
        resolvedComment.setContent(actualComment.getContent());
        resolvedComment.setId(actualComment.getId());
        resolvedComment.setAuthor(resolveUserWithoutMappings(actualComment.getAuthor()));
        resolvedComment.setCreatedAt(actualComment.getCreatedAt());
        resolvedComment.setUpdatedAt(actualComment.getUpdatedAt());
        return resolvedComment;
    }

    @Override
    public Category resolveCategory(Category actualCategory) {
        Category resolvedCategory = new Category();
        resolvedCategory.setId(actualCategory.getId());
        resolvedCategory.setType(actualCategory.getType());
        resolvedCategory.setName(actualCategory.getName());
        return resolvedCategory;
    }

    public Users resolveUserWithoutMappings(Users actualUser) {
        if(actualUser == null)
            return null;
        Users resolvedUser = new Users();
        resolvedUser.setId(actualUser.getId());
        resolvedUser.setName(actualUser.getName());
        resolvedUser.setEmail(actualUser.getEmail());
        resolvedUser.setCreatedAt(actualUser.getCreatedAt());
        resolvedUser.setUpdatedAt(actualUser.getUpdatedAt());
        return resolvedUser;
    }

    public List<Users> resolveUsersListWithoutMappings(List<Users> actualUsersList) {
        if(actualUsersList == null)
            return null;
        List<Users> resolvedUsersList = new ArrayList<>();
        actualUsersList.forEach((actualUser -> {
            resolvedUsersList.add(resolveUserWithoutMappings(actualUser));
        }));
        return  resolvedUsersList;
    }

    public Group resolveGroupWithoutMappings(Group actualGroup) {
        if(actualGroup == null)
            return null;
        Group resolvedGroup = new Group();
        resolvedGroup.setId(actualGroup.getId());
        resolvedGroup.setName(actualGroup.getName());
        resolvedGroup.setCreatedAt(actualGroup.getCreatedAt());
        resolvedGroup.setUpdatedAt(actualGroup.getUpdatedAt());
        resolvedGroup.setCreatedBy(resolveUserWithoutMappings(actualGroup.getCreatedBy()));
        return resolvedGroup;
    }

    public List<Group> resolveGroupListWithoutMappings(List<Group> actualGroupList) {
        if(actualGroupList == null)
            return null;
        List<Group> resolvedGroupList = new ArrayList<>();
        actualGroupList.forEach((actualGroup -> {
            resolvedGroupList.add(resolveGroupWithoutMappings(actualGroup));
        }));
        return resolvedGroupList;
    }

    public List<Debt> resolveDebtListWithoutGroup(List<Debt> actualDebtList) {
        if(actualDebtList == null)
            return null;
        List<Debt> resolvedDebtList = new ArrayList<>();
        actualDebtList.forEach((actualDebt -> {
            Debt resolvedDebt = new Debt();
            resolvedDebt.setId(actualDebt.getId());
            resolvedDebt.setAmount(actualDebt.getAmount());
            resolvedDebt.setFromUser(resolveUserWithoutMappings(actualDebt.getFromUser()));
            resolvedDebt.setToUser(resolveUserWithoutMappings(actualDebt.getToUser()));
            resolvedDebtList.add(resolvedDebt);
        }));
        return resolvedDebtList;
    }

    public List<Debt> resolveDebtList(List<Debt> actualDebtList) {
        if(actualDebtList == null)
            return null;
        List<Debt> resolvedDebtList = new ArrayList<>();
        actualDebtList.forEach((actualDebt -> {
            Debt resolvedDebt = new Debt();
            resolvedDebt.setId(actualDebt.getId());
            resolvedDebt.setAmount(actualDebt.getAmount());
            resolvedDebt.setFromUser(resolveUserWithoutMappings(actualDebt.getFromUser()));
            resolvedDebt.setToUser(resolveUserWithoutMappings(actualDebt.getToUser()));
            resolvedDebt.setGroup(resolveGroupWithoutMappings(actualDebt.getGroup()));
            resolvedDebtList.add(resolvedDebt);
        }));
        return resolvedDebtList;
    }

    public List<GroupBalance> resolveGroupBalanceList(List<GroupBalance> actualGroupBalanceList) {
        if(actualGroupBalanceList == null)
            return null;
        List<GroupBalance> resolvedGroupBalanceList = new ArrayList<>();
        actualGroupBalanceList.forEach((actualGroupBalance -> {
            GroupBalance resolvedGroupBalance = new GroupBalance();
            resolvedGroupBalance.setId(actualGroupBalance.getId());
            resolvedGroupBalance.setAmount(actualGroupBalance.getAmount());
            resolvedGroupBalance.setUser(resolveUserWithoutMappings(actualGroupBalance.getUser()));
            resolvedGroupBalanceList.add(resolvedGroupBalance);
        }));
        return resolvedGroupBalanceList;
    }

    public List<ExpenseDebts> resolveExpenseDebtList(List<ExpenseDebts> actualExpenseDebtList) {
        if(actualExpenseDebtList == null)
            return null;
        List<ExpenseDebts> resolvedExpenseDebtList = new ArrayList<>();
        actualExpenseDebtList.forEach((actualExpenseDebt -> {
            ExpenseDebts resolvedExpenseDebt = new ExpenseDebts();
            resolvedExpenseDebt.setId(actualExpenseDebt.getId());
            resolvedExpenseDebt.setNetBalance(actualExpenseDebt.getNetBalance());
            resolvedExpenseDebt.setAmountPaid(actualExpenseDebt.getAmountPaid());
            resolvedExpenseDebt.setBillingShare(actualExpenseDebt.getBillingShare());
            resolvedExpenseDebt.setUser(resolveUserWithoutMappings(actualExpenseDebt.getUser()));
            resolvedExpenseDebtList.add(resolvedExpenseDebt);
        }));
        return resolvedExpenseDebtList;
    }

    public List<Repayments> resolveRepaymentList(List<Repayments> actualRepaymentList) {
        if(actualRepaymentList == null)
            return null;
        List<Repayments> resolvedRepaymentsList = new ArrayList<>();
        actualRepaymentList.forEach((actualRepayment -> {
            Repayments resolvedRepayment = new Repayments();
            resolvedRepayment.setId(actualRepayment.getId());
            resolvedRepayment.setAmount(actualRepayment.getAmount());
            resolvedRepayment.setFrom(resolveUserWithoutMappings(actualRepayment.getFrom()));
            resolvedRepayment.setTo(resolveUserWithoutMappings(actualRepayment.getTo()));
            resolvedRepaymentsList.add(resolvedRepayment);
        }));
        return resolvedRepaymentsList;
    }

    public Category resolveCategoryWithoutMappings(Category actualCategory) {
        if(actualCategory == null)
            return null;
        Category resolvedCategory = new Category();
        resolvedCategory.setId(actualCategory.getId());
        resolvedCategory.setName(actualCategory.getName());
        resolvedCategory.setType(actualCategory.getType());
        return resolvedCategory;
    }

    public List<Comment> resolveCommentList(List<Comment> actualCommentList) {
        if(actualCommentList == null)
            return null;
        List<Comment> resolvedCommentList = new ArrayList<>();
        actualCommentList.forEach((actualComment -> {
            Comment resolvedComment = new Comment();
            resolvedComment.setId(actualComment.getId());
            resolvedComment.setAuthor(resolveUserWithoutMappings(actualComment.getAuthor()));
            resolvedComment.setContent(actualComment.getContent());
            resolvedCommentList.add(resolvedComment);
        }));
        return resolvedCommentList;
    }

    public Notify resolveNotify(Notify actualNotify) {
        if(actualNotify==null)
            return null;
        Notify resolvedNotify = new Notify();
        resolvedNotify.setId(actualNotify.getId());
        resolvedNotify.setForAddingExpense(actualNotify.getForAddingExpense());
        resolvedNotify.setForAddingFriend(actualNotify.getForAddingFriend());
        resolvedNotify.setForAddingGroup(actualNotify.getForAddingGroup());
        resolvedNotify.setForCommenting(actualNotify.getForCommenting());
        resolvedNotify.setForEditingExpense(actualNotify.getForEditingExpense());
        resolvedNotify.setForSettleUps(actualNotify.getForSettleUps());
        return resolvedNotify;
    }

}
