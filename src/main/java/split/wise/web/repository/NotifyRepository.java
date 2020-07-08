package split.wise.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Notify;
import split.wise.web.model.Users;


public interface NotifyRepository extends JpaRepository<Notify, Long> {
    Notify findByUser(Users users);
}
