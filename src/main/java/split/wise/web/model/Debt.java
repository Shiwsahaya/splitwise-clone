package split.wise.web.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long amount;

    @ManyToOne
    private Group group;

    @NotNull
    @ManyToOne
    private Users fromUser;

    @NotNull
    @ManyToOne
    private Users toUser;

    public Debt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Users getFromUser() {
        return fromUser;
    }

    public void setFromUser(Users user) {
        this.fromUser = user;
    }

    public Users getToUser() {
        return toUser;
    }

    public void setToUser(Users toUser) {
        this.toUser = toUser;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
