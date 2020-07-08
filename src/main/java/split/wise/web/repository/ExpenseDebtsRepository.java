package split.wise.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import split.wise.web.model.ExpenseDebts;
import split.wise.web.model.Users;

import java.util.List;


public interface ExpenseDebtsRepository extends JpaRepository<ExpenseDebts,Long> {

    List<ExpenseDebts> findAllByUser(Users user);

    void deleteAllByExpenseId(Long id);

}
