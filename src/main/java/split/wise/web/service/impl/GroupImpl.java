package split.wise.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.*;
import split.wise.web.repository.GroupRepository;
import split.wise.web.service.GroupService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class GroupImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepo;

    @Override
    public Group save(Group group){
        return groupRepo.save(group);
    }

    @Override
    public Group get(Long id){
        Optional<Group>result= groupRepo.findById(id);
        return result.get();
    }

    @Override
    public List<Group> getAll() {
        return (List<Group>) groupRepo.findAll();
    }

    @Override
    public List<Group> getAllByName(String name) {
        return groupRepo.findAllByName(name);
    }

}
