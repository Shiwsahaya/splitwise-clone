package split.wise.web.service;

import split.wise.web.model.Debt;
import split.wise.web.model.Group;
import split.wise.web.model.Users;

public interface DebtService {

    Debt save(Debt debt);

    Debt save(Group group, Users fromUser, Users toUser);

    Debt get(Users fromUser, Users toUser, Group group);

}
