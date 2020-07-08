package split.wise.web.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class ExpenseDebts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long billingShare;

    @NotNull
    private Long amountPaid;

    @NotNull
    private Long netBalance;

    @NotNull
    @ManyToOne
    private Expense expense;

    @NotNull
    @ManyToOne
    private Users user;

    @JsonCreator
    public ExpenseDebts(@JsonProperty("user") Long userId) {
        this.user = new Users();
        user.setId(userId);
    }

    public ExpenseDebts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillingShare() {
        return billingShare;
    }

    public void setBillingShare(Long billingShare) {
        this.billingShare = billingShare;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Long getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Long getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(Long netBalance) {
        this.netBalance = netBalance;
    }

}
