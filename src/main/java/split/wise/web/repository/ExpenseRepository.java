package split.wise.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.Expense;
import split.wise.web.model.Users;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

   List<Expense> findByAddedBy(Users users);

}
