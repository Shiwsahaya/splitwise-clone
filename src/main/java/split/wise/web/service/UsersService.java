package split.wise.web.service;

import split.wise.web.model.Users;

import java.util.List;

public interface UsersService {

    Users save(Users user);

    Users get(Long id);

    Users get(String email);

    List<Users> getAll();

    List<Users> getAllByName(String name);

    void makeFriends(Users user1, Users user2);

}
