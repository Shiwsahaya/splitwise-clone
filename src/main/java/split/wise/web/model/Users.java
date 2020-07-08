package split.wise.web.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
public class Users extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String password;

    @NotNull
    private Long money;

    @ManyToMany()
    private List<Users> friends;

    @OneToMany(mappedBy = "addedBy")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "createdBy")
    private List<Group> createdGroups;

    @ManyToMany(mappedBy = "members")
    private List<Group> contributingGroups;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "fromUser")
    private List<Debt> debts;

    @OneToMany(mappedBy = "user")
    private List<GroupBalance> groupBalances;

    @OneToOne(mappedBy = "user")
    private Notify notify;

    public List<GroupBalance> getGroupBalances() {
        return groupBalances;
    }

    public void setGroupBalances(List<GroupBalance> groupBalances) {
        this.groupBalances = groupBalances;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public List<Users> getFriends() {
        return friends;
    }

    public void setFriends(List<Users> friends) {
        this.friends = friends;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Group> getCreatedGroups() {
        return createdGroups;
    }

    public void setCreatedGroups(List<Group> createdGroups) {
        this.createdGroups = createdGroups;
    }

    public List<Group> getContributingGroups() {
        return contributingGroups;
    }

    public void setContributingGroups(List<Group> contributingGroups) {
        this.contributingGroups = contributingGroups;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public void setDebts(List<Debt> debts) {
        this.debts = debts;
    }

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }

}
