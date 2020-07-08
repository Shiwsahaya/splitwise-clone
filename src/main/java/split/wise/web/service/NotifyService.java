package split.wise.web.service;


import split.wise.web.model.Notify;
import split.wise.web.model.Users;


public interface NotifyService {

    Notify save(Notify notify);

    Notify defaultSave(Users user);

    Notify findByUser(Users users);

}
