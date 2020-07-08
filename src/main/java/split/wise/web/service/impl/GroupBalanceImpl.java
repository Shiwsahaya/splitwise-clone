package split.wise.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.Group;
import split.wise.web.model.GroupBalance;
import split.wise.web.model.Users;
import split.wise.web.repository.GroupBalanceRepository;
import split.wise.web.service.GroupBalanceService;

import javax.transaction.Transactional;


@Service
@Transactional
public class GroupBalanceImpl implements GroupBalanceService {

    @Autowired
    GroupBalanceRepository groupBalanceRepo;

    @Override
    public GroupBalance save(GroupBalance groupBalance) {
        return groupBalanceRepo.save(groupBalance);
    }

    @Override
    public GroupBalance save(Users user, Group group) {
        GroupBalance groupBalance = new GroupBalance();
        groupBalance.setAmount(0L);
        groupBalance.setGroup(group);
        groupBalance.setUser(user);
        return groupBalanceRepo.save(groupBalance);
    }

    @Override
    public GroupBalance get(Users user, Group group) {
        return groupBalanceRepo.findByUserAndGroup(user, group);
    }

}
