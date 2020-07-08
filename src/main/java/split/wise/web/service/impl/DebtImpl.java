package split.wise.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.Debt;
import split.wise.web.model.Group;
import split.wise.web.model.Users;
import split.wise.web.repository.DebtRepository;
import split.wise.web.service.DebtService;

import javax.transaction.Transactional;


@Service
@Transactional
public class DebtImpl implements DebtService {

    @Autowired
    private DebtRepository debtRepository;

    public Debt save(Debt debt){
         return debtRepository.save(debt);
    }

    @Override
    public Debt save(Group group, Users fromUser, Users toUser) {
        Debt debt = new Debt();
        debt.setAmount(0L);
        debt.setGroup(group);
        debt.setFromUser(fromUser);
        debt.setToUser(toUser);
        return debtRepository.save(debt);
    }

    @Override
    public Debt get(Users fromUser, Users toUser, Group group) {
        return debtRepository.findByFromUserAndToUserAndGroup(fromUser, toUser, group);
    }

}
