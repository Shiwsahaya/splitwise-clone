package split.wise.web.service;

import split.wise.web.model.Group;

import java.util.List;

public interface GroupService {

    Group save(Group group);

    Group get(Long id);

    List<Group> getAll();

    List<Group> getAllByName(String name);

}
