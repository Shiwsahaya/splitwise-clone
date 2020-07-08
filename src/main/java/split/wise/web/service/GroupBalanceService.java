package split.wise.web.service;


import split.wise.web.model.Group;
import split.wise.web.model.GroupBalance;
import split.wise.web.model.Users;

public interface GroupBalanceService {

    GroupBalance save(GroupBalance groupBalance);

    GroupBalance save(Users user, Group group);

    GroupBalance get(Users user, Group group);

}
