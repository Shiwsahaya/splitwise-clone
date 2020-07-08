package split.wise.web.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Group extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Users createdBy;

    @NotNull
    @ManyToMany
    private List<Users> members;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "group")
    private List<Debt>debts;

    @OneToMany(mappedBy = "group")
    private List<GroupBalance> groupBalances;

    @OneToMany(mappedBy = "group")
    private List<Activity> activities;

    @JsonCreator
    public Group(@JsonProperty("createdBy") Long createdById,
                 @JsonProperty("members") List<Long> membersId) {
        this.createdBy = new Users();
        createdBy.setId(createdById);
        this.members = new ArrayList<>();
        membersId.forEach((id -> {
            Users member = new Users();
            member.setId(id);
            members.add(member);
        }));
    }

    public Group() {
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

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public List<Users> getMembers() {
        return members;
    }

    public void setMembers(List<Users> members) {
        this.members = members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public void setDebts(List<Debt> debts) {
        this.debts = debts;
    }

    public List<GroupBalance> getGroupBalances() {
        return groupBalances;
    }

    public void setGroupBalances(List<GroupBalance> groupBalances) {
        this.groupBalances = groupBalances;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

}
