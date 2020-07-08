package split.wise.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.Users;
import split.wise.web.repository.UsersRepository;
import split.wise.web.service.DebtService;
import split.wise.web.service.UsersService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UsersImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepo;

    @Autowired
    private DebtService debtService;

    public Users save(Users users){
      return usersRepo.save(users);
    }

    public Users get(Long id){
        Optional<Users>result= usersRepo.findById(id);
        return result.get();
    }

    @Override
    public Users get(String email) {
        return usersRepo.findByEmail(email);
    }

    @Override
    public List<Users> getAll() {
        return (List<Users>) usersRepo.findAll();
    }

    @Override
    public List<Users> getAllByName(String name) {
        return usersRepo.findAllByName(name);
    }

    @Override
    public void makeFriends(Users user1, Users user2) {
        if(user1.getFriends() != null) {
            if(user1.getFriends().contains(user2))
                return;
            List<Users> friends = new ArrayList<>(user1.getFriends());
            friends.add(user2);
            user1.setFriends(friends);
        } else {
            user1.setFriends(Collections.singletonList(user2));
        }
        debtService.save(null, user1, user2);
        usersRepo.save(user1);
    }

}
