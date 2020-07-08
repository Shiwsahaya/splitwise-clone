package split.wise.web.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Repayments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long amount;

    @NotNull
    @ManyToOne
    private Expense expense;

    @NotNull
    @ManyToOne
    private Users from;

    @NotNull
    @ManyToOne
    private Users to;

    @JsonCreator
    public Repayments(@JsonProperty("to") Long toId,
                      @JsonProperty("from") Long fromId) {
        this.to = new Users();
        to.setId(toId);
        this.from = new Users();
        from.setId(fromId);
    }

    public Repayments() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Users getFrom() {
        return from;
    }

    public void setFrom(Users fromUser) {
        this.from = fromUser;
    }

    public Users getTo() {
        return to;
    }

    public void setTo(Users toUser) {
        this.to = toUser;
    }

}
