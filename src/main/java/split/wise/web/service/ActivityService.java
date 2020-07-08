package split.wise.web.service;


import split.wise.web.model.Activity;
import split.wise.web.model.Users;

import java.util.List;


public interface ActivityService {

    void save(Activity activity);

    List<Activity> getAllByUser(Users user);

}
