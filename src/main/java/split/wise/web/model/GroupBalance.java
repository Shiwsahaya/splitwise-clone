package split.wise.web.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class GroupBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long amount;

    @NotNull
    @ManyToOne
    private Group group;

    @NotNull
    @ManyToOne
    private Users user;

    public GroupBalance() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
