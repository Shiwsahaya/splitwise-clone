package split.wise.web.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Notify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Users user;

    @NotNull
    private Boolean forAddingGroup;

    @NotNull
    private Boolean forAddingFriend;

    @NotNull
    private Boolean forAddingExpense;

    @NotNull
    private Boolean forEditingExpense;

    @NotNull
    private Boolean forCommenting;

    @NotNull
    private Boolean forSettleUps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Boolean getForAddingGroup() {
        return forAddingGroup;
    }

    public void setForAddingGroup(Boolean forAddingGroup) {
        this.forAddingGroup = forAddingGroup;
    }

    public Boolean getForAddingFriend() {
        return forAddingFriend;
    }

    public void setForAddingFriend(Boolean forAddingFriend) {
        this.forAddingFriend = forAddingFriend;
    }

    public Boolean getForAddingExpense() {
        return forAddingExpense;
    }

    public void setForAddingExpense(Boolean forAddingExpense) {
        this.forAddingExpense = forAddingExpense;
    }

    public Boolean getForEditingExpense() {
        return forEditingExpense;
    }

    public void setForEditingExpense(Boolean forEditingExpense) {
        this.forEditingExpense = forEditingExpense;
    }

    public Boolean getForCommenting() {
        return forCommenting;
    }

    public void setForCommenting(Boolean forCommenting) {
        this.forCommenting = forCommenting;
    }

    public Boolean getForSettleUps() {
        return forSettleUps;
    }

    public void setForSettleUps(Boolean forSettleUps) {
        this.forSettleUps = forSettleUps;
    }

}
