package split.wise.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.Notify;
import split.wise.web.model.Users;
import split.wise.web.repository.NotifyRepository;
import split.wise.web.service.NotifyService;


@Service
public class NotifyImpl implements NotifyService {

    @Autowired
    private NotifyRepository notifyRepo;

    @Override
    public Notify save(Notify notify) {
        return notifyRepo.save(notify);
    }

    @Override
    public Notify defaultSave(Users user) {
        Notify notify = new Notify();
        notify.setUser(user);
        notify.setForAddingExpense(true);
        notify.setForAddingGroup(true);
        notify.setForCommenting(true);
        notify.setForAddingFriend(true);
        notify.setForEditingExpense(true);
        notify.setForSettleUps(true);
        return notifyRepo.save(notify);
    }
    @Override
    public Notify findByUser(Users users){
        return notifyRepo.findByUser(users);
    }

}
